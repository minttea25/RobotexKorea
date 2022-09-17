package Setup;

import ConstantValues.Constants;
import ConstantValues.FormationOrTicket;
import ConstantValues.Sections;
import Excel.ExcelReadManager;
import Excel.SheetWriteFormation;
import Excel.SheetWriteTicket;
import Model.TeamModel;
import Model.SetupDataModel;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Setup {
    private static Setup Instance = null;
    SetupDataModel setupData;
    boolean loaded = false;
    boolean cannotFindFile = true;
    Map<Sections, List<TeamModel>> teamData;
    Map<Sections, Map<Integer, List<TeamModel>>> entryMaps = new HashMap<>();
    Map<Sections, Map<Integer, List<TeamModel>>> ticketMaps = new HashMap<>();
    Map<Sections, Boolean> status = new HashMap<>();
    Map<Sections, Boolean> writeStatus = new HashMap<>();

    String checkNumErrorList = "";



    public static Setup getInstance() {
        if (Instance == null) {
            Instance = new Setup();
        }

        return Instance;
    }

    public void loadSetup(String filePath) {
        checkNumErrorList = "";
        if (teamData!= null) teamData.clear();
        entryMaps.clear();
        ticketMaps.clear();
        status.clear();
        writeStatus.clear();
        loaded = false;
        cannotFindFile = true;

        File file = new File(filePath);

        if (!file.exists()) {
            cannotFindFile = false;
            return;
        }

        ExcelReadManager erm = new ExcelReadManager(filePath);
        erm.loadFile();

        if (erm.getData() == null || erm.getData().size() == 0) {
            loaded = false;
        }
        else {
            teamData = erm.getData();
            loaded = true;
        }

        if (erm.isUserValueSheetLoaded()) {
            setupData = erm.getUserValues();
            loaded = true;
        }
        else {
            loaded = false;
        }
    }

    private void saveFilesFormation() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.SAVE_FILE_DATE_FORMAT);

        ExecutorService service = Executors.newFixedThreadPool(status.size());
        Future<Boolean>[] futures = new Future[status.size()];

        // 로드 된 data 에 대해서만 수행
        SetFormation();

        SheetWriteFormation[] swr = new SheetWriteFormation[status.size()];

        try {
            int idx=0;
            for (Sections s : status.keySet()) {
                if (!status.get(s)) {
                    continue;
                }
                String fileName = s.toString() + " - " + date + " " + time.format(timeFormatter);
                swr[idx] = new SheetWriteFormation(
                        s,
                        entryMaps.get(s),
                        fileName
                );
                futures[idx] = service.submit(swr[idx]);
                idx++;
            }
            idx = 0;
            for (Sections s : status.keySet()) {
                if (!status.get(s)) {
                    continue;
                }
                if (!futures[idx].get()) {
                    writeStatus.put(s, false);
                }
                else {
                    writeStatus.put(s, true);
                }
                idx++;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private void saveFilesTicket() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.SAVE_FILE_DATE_FORMAT);

        ExecutorService service = Executors.newFixedThreadPool(status.size());
        Future<Boolean>[] futures = new Future[status.size()];

        // 로드 된 data 에 대해서만 수행
        SetTicket();

        SheetWriteTicket[] swt = new SheetWriteTicket[status.size()];

        try {
            int idx=0;
            for (Sections s : status.keySet()) {
                if (!status.get(s)) {
                    continue;
                }
                String fileName = s.toString() + " - " + date + " " + time.format(timeFormatter);
                int num = setupData.getValue(FormationOrTicket.Ticket, s);
                swt[idx] = new SheetWriteTicket(
                        s,
                        ticketMaps.get(s),
                        fileName,
                        num
                );
                futures[idx] = service.submit(swt[idx]);
                idx++;
            }
            idx = 0;
            for (Sections s : status.keySet()) {
                if (!status.get(s)) {
                    continue;
                }
                if (!futures[idx].get()) {
                    writeStatus.put(s, false);
                }
                else {
                    writeStatus.put(s, true);
                }
                idx++;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    public void saveFiles() {
        shuffleData();

        saveFilesFormation();
        saveFilesTicket();
    }

    // after clicking save btn, it must be called after 'load excel'
    public boolean checkSetupValues(FormationOrTicket fun) {
        boolean flag = true;

        if (fun == FormationOrTicket.Formation) {
            for (Sections s : teamData.keySet()) {
                int num = setupData.getValue(fun, s);

                if(teamData.get(s).size() < num) {
                    checkNumErrorList += "\n" + fun + s + ": " + teamData.get(s).size();
                    status.put(s, false);
                    flag = false;
                }
                else {
                    status.put(s, true);
                }
            }
        }

        return flag;
    }

    private void shuffleData() {
        for(Sections s : teamData.keySet()) {
            Collections.shuffle(teamData.get(s));
        }
        for(Sections s : teamData.keySet()) {
            Collections.shuffle(teamData.get(s));
        }
    }

    private void SetFormation() {
        for (Sections s : teamData.keySet()) {
            // Formation 은 roboleague 없음!
            if (!status.get(s) || s == Sections.RoboLeague) {
                continue;
            }
            String teamNumber = "";
            switch (s) {
                case LegoSumo1kg: teamNumber = "Sumo1-"; break;
                case LegoSumo3kg: teamNumber = "Sumo3-"; break;
                case LineFollowingE: teamNumber = "LineE-"; break;
                case LineFollowingJH: teamNumber = "LineJH-"; break;
                case LegoFolkraceE: teamNumber = "FolkE-"; break;
                case LegoFolkraceJH: teamNumber = "FolkJH-"; break;
            }

            entryMaps.put(s, new HashMap<>());
            int num = setupData.getValue(FormationOrTicket.Formation, s);
            for (int i=0; i<num; i++) {
                entryMaps.get(s).put(i, new ArrayList<>());
            }

            int i = 0;
            int t = 1;
            char c = 'A';
            for (TeamModel team : teamData.get(s)) {
                team.setTeamNumber(teamNumber + (char)(c+i) + "-" + t);

                entryMaps.get(s).get(i).add(team);
                if (i >= entryMaps.get(s).size() - 1) {
                    i = 0;
                    t++;
                    continue;
                }
                i++;
            }

            /*System.out.println(s);
            for (List<TeamModel> l : entryMaps.get(s).values()) {
                for (TeamModel m : l) {
                    System.out.println(m);
                }
                System.out.println("-----------------------");
            }*/
        }
    }

    private void SetTicket() {
        for (Sections s : teamData.keySet()) {
            if (!status.get(s)) {
                continue;
            }
            ticketMaps.put(s, new HashMap<>());
            int num = setupData.getValue(FormationOrTicket.Ticket, s);
            ticketMaps.get(s).put(0, new ArrayList<>());
            ticketMaps.get(s).put(1, new ArrayList<>());
            ticketMaps.get(s).put(2, new ArrayList<>());

            for (int i=0; i<teamData.get(s).size(); i++) {
                // world ticket (1배수)
                if (i < num) {
                    ticketMaps.get(s).get(0).add(teamData.get(s).get(i));
                }
                // 2의 배수
                else if (i < num*3) {
                    ticketMaps.get(s).get(1).add(teamData.get(s).get(i));
                }
                else {
                    ticketMaps.get(s).get(2).add(teamData.get(s).get(i));
                }
            }
        }
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isCannotFindFile() {
        return cannotFindFile;
    }

    public Map<Sections, Boolean> getStatus() {
        return status;
    }

    public SetupDataModel getSetupData() {
        return setupData;
    }

    public Map<Sections, Map<Integer, List<TeamModel>>> getEntryMaps() {
        return entryMaps;
    }

    public Map<Sections, Map<Integer, List<TeamModel>>> getTicketMaps() {
        return ticketMaps;
    }

    public String getCheckNumErrorList() {
        return checkNumErrorList;
    }

    public Map<Sections, Boolean> getWriteStatus() {
        return writeStatus;
    }

    public List<TeamModel> getTeamDataBySection(Sections section) {
        List<TeamModel> t = teamData.get(section);

        if (t == null ) {
            return null;
        }
        else {
            return t;
        }
    }
}
