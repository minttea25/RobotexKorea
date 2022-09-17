package Excel;

import ConstantValues.Sections;
import ConstantValues.SettingValues;
import Model.TeamModel;
import Model.SetupDataModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExcelReadManager {
    String filePath;
    Map<Sections, List<TeamModel>> data = new HashMap<>();
    Map<Sections, Boolean> dataLoaded = new HashMap<>();
    SetupDataModel userValues = null;

    boolean fullDataSheetsLoaded;
    boolean userValueSheetLoaded;

    Workbook workbook;
    InputStream is;

    public ExcelReadManager(String filePath) {
        this.fullDataSheetsLoaded = false;

        // check the file exist
        if (!(new File(filePath).exists())) {
            //System.out.println("There is no file: " + filePath);
            return;
        }
        this.filePath = filePath;
    }

    public boolean setFilePath(String filePath) {
        // check the file exist
        if (!(new File(filePath).exists())) {
            //System.out.println("There is no file: " + filePath);
            return false;
        }
        this.filePath = filePath;
        return true;
    }

    public void loadFile() {
        try {
            is = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(is);

            loadAllSheet();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAllSheet() {
        fullDataSheetsLoaded = true;

        // check availability sheet name
        int availableSheetNum = 0;
        int availableSettingSheetNum = 0;
        int settingSheetIndex = -1;
        List<Integer> availableIndex = new ArrayList<>();
        for (int i=0; i<workbook.getNumberOfSheets(); i++) {
            String sheetName = workbook.getSheetAt(i).getSheetName();
            try {
                if (SettingValues.getInstance().getData().getSETTING_SHEET_NAME().equals(sheetName)) {
                    settingSheetIndex = i;
                    availableIndex.add(i);
                    availableSettingSheetNum++;
                    continue;
                }

                Sections.valueOf(sheetName);
                availableIndex.add(i);
                availableSheetNum++;
            } catch (IllegalArgumentException e) {
                //System.out.println("wrong sheet name: " + sheetName);
                fullDataSheetsLoaded = false;
            }
        }

        if (availableSettingSheetNum != 1) {
            userValueSheetLoaded = false;
            return;
        }

        ExecutorService service = Executors.newFixedThreadPool(availableSheetNum + availableSettingSheetNum);

        SheetReadManager[] srms = new SheetReadManager[availableSheetNum];
        Future<List<TeamModel>>[] futures = new Future[availableSheetNum];

        SheetReadManagerRobotex srmr = null;
        Future<SetupDataModel> future = null;

        try {
            int i=0;
            for (int index : availableIndex) {
                if (index == settingSheetIndex) {
                    srmr = new SheetReadManagerRobotex(workbook.getSheetAt(index));
                    future = service.submit(srmr);
                    continue;
                }

                srms[i] = new SheetReadManager(workbook.getSheetAt(index));
                futures[i] = service.submit(srms[i]);

                i++;
            }

            for (i=0; i<availableSheetNum; i++) {
                List<TeamModel> t = futures[i].get();
                if (t == null) {
                    dataLoaded.put(Sections.valueOf(srms[i].getSheet().getSheetName()), false);

                    //System.out.println("Sheet loading failed at Sheet: " + srms[i].getSheet().getSheetName());
                }
                else {
                    data.put(Sections.valueOf(srms[i].getSheet().getSheetName()), t);
                    dataLoaded.put(Sections.valueOf(srms[i].getSheet().getSheetName()), true);
                }
            }

            if (availableSettingSheetNum == 1) {
                if (future != null) {
                    userValues = future.get();
                }

                if (userValues == null) {
                    userValueSheetLoaded = false;
                }
                else {
                    userValueSheetLoaded = true;
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    public Map<Sections, List<TeamModel>> getData() {
        return data;
    }

    public List<TeamModel> getData(Sections section) {
        try {
            return data.get(section);
        } catch (NullPointerException e) {
            //System.out.println("There is no loaded sheet name: " + section);
            return null;
        }
    }

    public Map<Sections, Boolean> getDataLoaded() {
        return dataLoaded;
    }

    public boolean getDataLoaded(Sections section) {
        try {
            return dataLoaded.get(section);
        } catch (NullPointerException e) {
            // System.out.println("There is no loaded sheet name: " + section);
            return false;
        }
    }

    public SetupDataModel getUserValues() {
        return userValues;
    }

    public boolean isUserValueSheetLoaded() {
        return userValueSheetLoaded;
    }
}
