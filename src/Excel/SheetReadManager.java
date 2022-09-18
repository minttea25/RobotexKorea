package Excel;

import Model.Member;
import Model.TeamModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class SheetReadManager extends AExcelReadManager implements Callable {
    SheetReadManager(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public List<TeamModel> call() {
        List<TeamModel> teamData = new ArrayList<>();

        try {
            Iterator<Row> rowItr = sheet.iterator();

            while (rowItr.hasNext()) {
                TeamModel team = new TeamModel();
                List<Member> members = new ArrayList<>();
                String mName = null;
                String mSchool = null;
                String mGrade = null;

                Row row = rowItr.next();

                // If first row is header, then skip the first row and start loading at 2nd row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellItr = row.cellIterator();
                boolean valid = false;
                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();
                    int columnIndex = cell.getColumnIndex();

                    // No 데이터가 존재하는지 확인
                    // 없다면 해당 라인이 마지막 라인으로 인식
                    if (columnIndex == 0) {
                        valid = true;
                    }

                    switch (columnIndex) {
                        case 0: // no - pass this value
                            // 빈 칸 데이터가 있을 경우도 데이터가 없는 것으로 간주해야함
                            Object o = getValueFromCell(cell);
                            if (o == null || o.toString().trim().equals("")) {
                                valid = false;
                            }
                            break;
                        case 1: // TeamName - String
                            team.setTeamName(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 2: // Institute - String
                            team.setInstitute(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 3: // Mentor - String
                            team.setMentor(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 4: // Mentor Email - String
                            team.setMentorEmail(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 5: // Mentor Phone - String
                            team.setMentorPhone(String.valueOf(getValueFromCell(cell)));
                            break;

                        case 6: case 9: case 12: case 15: case 18: // Member1 name
                            mName = String.valueOf(getValueFromCell(cell));
                            break;
                        case 7: case 10: case 13: case 16: case 19: // Member1 school
                            mSchool = String.valueOf(getValueFromCell(cell));
                            break;
                        case 8: case 11: case 14: case 17: case 20: // Member1 grade
                            mGrade = String.valueOf(getValueFromCell(cell));
                            members.add(new Member(mName, mSchool, mGrade));
                            break;

                        default: // no more data
                            break;
                    }
                    // invalid 한 라인일 경우 데이터의 끝으로 간주하여 읽기 stop
                    if (!valid) {
                        break;
                    }
                }
                if (valid) {
                    team.setMembers(members);
                    teamData.add(team);
                }
                else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teamData;
    }
}
