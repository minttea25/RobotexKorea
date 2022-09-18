package Excel;

import ConstantValues.Sections;
import ConstantValues.SettingValues;
import Model.SettingModel;
import Model.SetupDataModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;
import java.util.concurrent.Callable;

public class SheetReadManagerRobotex extends AExcelReadManager implements Callable {
    public SheetReadManagerRobotex(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public SetupDataModel call() {
        SetupDataModel values = new SetupDataModel();
        SettingModel v = SettingValues.getInstance().getData();
        int read = 13;

        Iterator<Row> rowItr = sheet.iterator();

        while (rowItr.hasNext()) {
            Row row = rowItr.next();

            int rowIndex = row.getRowNum();
            Iterator<Cell> cellItr = row.cellIterator();

            if (read == 0 || rowIndex >= 15) {
                break;
            }

            try {
                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();
                    int columnIndex = cell.getColumnIndex();

                    //System.out.println(rowIndex + ", " + columnIndex + ": " + String.valueOf(getValueFromCell(cell)));

                    // LEGO SUMO 1KG FORMATION
                    if (rowIndex == v.getLEGO_SUMO_1KG_FORMATION_CELL_Y() && columnIndex == v.getLEGO_SUMO_1KG_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_LegoSumo1kg(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO SUMO 3KG FORMATION
                    if (rowIndex == v.getLEGO_SUMO_3KG_FORMATION_CELL_Y() && columnIndex == v.getLEGO_SUMO_3KG_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_LegoSumo3kg(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO LINE E FORMATION
                    if (rowIndex == v.getLEGO_LINE_E_FORMATION_CELL_Y() && columnIndex == v.getLEGO_LINE_E_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_LineFollowingE(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO LINE JH FORMATION
                    if (rowIndex == v.getLEGO_LINE_JH_FORMATION_CELL_Y() && columnIndex == v.getLEGO_LINE_JH_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_LineFollowingJH(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO FOLK E FORMATION
                    if (rowIndex == v.getLEGO_FOLK_E_FORMATION_CELL_Y() && columnIndex == v.getLEGO_FOLK_E_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_FolkraceE(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO FOLK JH FORMATION
                    if (rowIndex == v.getLEGO_FOLK_JH_FORMATION_CELL_Y() && columnIndex == v.getLEGO_FOLK_JH_FORMATION_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setFormation_FolkraceJH(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO SUMO 1KG TICKET
                    if (rowIndex == v.getLEGO_SUMO_1KG_TICKET_CELL_Y() && columnIndex == v.getLEGO_SUMO_1KG_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_LegoSumo1kg(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO SUMO 3KG TICKET
                    if (rowIndex == v.getLEGO_SUMO_3KG_TICKET_CELL_Y() && columnIndex == v.getLEGO_SUMO_3KG_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_LegoSumo3kg(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO LINE E TICKET
                    if (rowIndex == v.getLEGO_LINE_E_TICKET_CELL_Y() && columnIndex == v.getLEGO_LINE_E_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_LineFollowingE(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO LINE JH TICKET
                    if (rowIndex == v.getLEGO_LINE_JH_TICKET_CELL_Y() && columnIndex == v.getLEGO_LINE_JH_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_LineFollowingJH(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO FOLK E TICKET
                    if (rowIndex == v.getLEGO_FOLK_E_TICKET_CELL_Y() && columnIndex == v.getLEGO_FOLK_E_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_FolkraceE(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO FOLK JH TICKET
                    if (rowIndex == v.getLEGO_FOLK_JH_TICKET_CELL_Y() && columnIndex == v.getLEGO_FOLK_JH_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_FolkraceJH(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    // LEGO ROBO LEAGUE TICKET
                    if (rowIndex == v.getLEGO_ROBOLEAGUE_TICKET_CELL_Y() && columnIndex == v.getLEGO_ROBOLEAGUE_TICKET_CELL_X()) {
                        if (getValueFromCell(cell) == null) {
                            return null;
                        }
                        values.setTicket_RoboLeague(Integer.parseInt(String.valueOf(getValueFromCell(cell))));
                        --read;
                        continue;
                    }

                    if (columnIndex >= 15) {
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                return null;
            }
        }
        return values;
    }
}
