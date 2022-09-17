package Model;

public class Member {
    String name;
    String school;
    String grade;

    public Member(String name, String school, String grade) {
        this.name = name;
        this.school = school;
        this.grade = grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", grade=" + grade +
                '}';
    }
}