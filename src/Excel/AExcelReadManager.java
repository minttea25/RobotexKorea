package Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class AExcelReadManager {
    protected Sheet sheet;

    public Sheet getSheet() {
        return sheet;
    }

    protected Object getValueFromCell(Cell cell) {
        switch ((cell.getCellType())) {
            case STRING:
                return cell.getStringCellValue();
//            case BOOLEAN :
//                return cell.getBooleanCellValue();
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue()); // caution: return DOUBLE -> String
//            case FORMULA:
//                return cell.getCellFormula();
            case BLANK :
                return null;
            default:
                return null;

        }
    }
}
