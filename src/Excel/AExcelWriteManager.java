package Excel;

import ConstantValues.Sections;
import ConstantValues.SettingValues;
import Utils.FileUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AExcelWriteManager {
    String saveDir;
    String saveFormationDir;
    String saveTicketDir;
    XSSFWorkbook workbook;
    Sections section;
    boolean written;
    String fileName;

    protected AExcelWriteManager() {
        saveDir = SettingValues.getInstance().getData().getEXCEL_SAVE_DIR();
        saveFormationDir = SettingValues.getInstance().getData().getFORMATION_SAVE_DIR();
        saveTicketDir = SettingValues.getInstance().getData().getTICKET_SAVE_DIR();
        createBaseFolder();
    }


    private void createBaseFolder() {
        if (!FileUtil.createFolder(FileUtil.getPath(SettingValues.getInstance().getData().getEXCEL_SAVE_DIR()))) {
            //System.out.println("Failed to create folder: " + SettingValues.getInstance().getData().getEXCEL_SAVE_DIR());
        }
        else {
            //System.out.println("Folder created");
        }
    }

    public void setCellValue(XSSFRow curRow, int columnIndex, String content) {
        try {
            // 기존 null 값을 Object로 캐스팅 하고 다시 String으로 캐스팅 하니까 "null" 이 되어 버렸음......................
            if (content ==  null || content.equals("null")) {
                curRow.createCell(columnIndex).setCellValue("");
            }
            else {
                curRow.createCell(columnIndex).setCellValue(content);
            }

        }
        catch (Exception e) {}
    }

    public void setCellValue(XSSFRow curRow, int columnIndex, int content) {
        try {
            curRow.createCell(columnIndex).setCellValue(content);
        }
        catch (Exception e) {}
    }

    abstract void createFolder();
    public abstract void createExcelFile() ;

    public boolean isWritten() {
        return written;
    }
}
