package Excel;

import ConstantValues.Constants;
import ConstantValues.Sections;
import ConstantValues.SettingValues;
import Model.SettingModel;
import Model.TeamModel;
import Utils.FileUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class SheetWriteFormation extends AExcelWriteManager implements Callable {
    Map<Integer, List<TeamModel>> data;

    public SheetWriteFormation(Sections section, Map<Integer, List<TeamModel>> data, String fileName) {
        super();

        this.section = section;
        this.data = data;
        this.fileName = "." + File.separator + Paths.get(super.saveDir, super.saveFormationDir) + File.separator + fileName;

        written = false;

        createFolder();
    }

    @Override
    void createFolder() {
        if (!FileUtil.createFolder(Paths.get(super.saveDir, super.saveFormationDir))) {
            //System.out.println("Failed to create folder: " + Constants.EXCEL_SAVE_PATH_FORMATION);
        }
    }

    @Override
    public void createExcelFile() {
        if (data == null || data.size() == 0) {
            //System.out.println("There is no data to write.");
            return;
        }

        FileOutputStream fos = null;
        String fName = fileName  + "."+ Constants.EXCEL_EXTENSION_XLSX;
        SettingModel v = SettingValues.getInstance().getData();

        try {
            int row = 0;

            fos = new FileOutputStream(fName);
            workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(section.toString());
            XSSFRow curRow;

            // write column name on top of the sheet
            curRow = sheet.createRow(row);
            curRow.createCell(0).setCellValue(v.getCOLUMN_ENTRY());
            curRow.createCell(1).setCellValue(v.getCOLUMN_TEAM_NUMBER());
            curRow.createCell(2).setCellValue(v.getCOLUMN_TEAM_NAME());
            curRow.createCell(3).setCellValue(v.getCOLUMN_INSTITUTE());
            curRow.createCell(4).setCellValue(v.getCOLUMN_MENTOR());
            curRow.createCell(5).setCellValue(v.getCOLUMN_MENTOR_EMAIL());
            curRow.createCell(6).setCellValue(v.getCOLUMN_MENTOR_PHONE());

            for (int i=0; i<5; i++) {
                curRow.createCell(7 + i*3).setCellValue(v.getCOLUMN_MEMBER_NAME() + (i+1));
                curRow.createCell(8 + i*3).setCellValue(v.getCOLUMN_MEMBER_SCHOOL() + (i+1));
                curRow.createCell(9 + i*3).setCellValue(v.getCOLUMN_MEMBER_GRADE() + (i+1));
            }

            for (int entryNumber : data.keySet()) {

                for (TeamModel team : data.get(entryNumber)) {
                    curRow = sheet.createRow(++row);
                    int membersNum = team.GetMemberSize();

                    curRow.createCell(0).setCellValue(entryNumber);
                    setCellValue(curRow, 1, team.getTeamNumber());
                    setCellValue(curRow, 2, team.getTeamName());
                    setCellValue(curRow, 3, team.getInstitute());
                    setCellValue(curRow, 4, team.getMentor());
                    setCellValue(curRow, 5, team.getMentorEmail());
                    setCellValue(curRow, 6, team.getMentorPhone());

                    for (int i=0; i<membersNum; i++) {
                        setCellValue(curRow, 7 + i*3, team.getMembers().get(i).getName());
                        setCellValue(curRow, 7 + i*3 + 1, team.getMembers().get(i).getSchool());
                        setCellValue(curRow, 7 + i*3 + 2, team.getMembers().get(i).getGrade());
                    }
                }
                row++;
            }

            workbook.write(fos);

            written = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!written) {
                if (FileUtil.deleteFile(Paths.get(fName))) {
                    //System.out.println("deleted file");
                }
            }
        }
    }
    @Override
    public Boolean call() {
        createExcelFile();
        return this.written;
    }
}
