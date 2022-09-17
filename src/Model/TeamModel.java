package Model;

import java.util.ArrayList;
import java.util.List;

public class TeamModel {
    String teamName;
    String institute;
    String mentor;
    String mentorEmail;
    String mentorPhone;
    List<Member> members = new ArrayList<>();

    String teamNumber;

    public TeamModel(){}

    public TeamModel(String teamName, String institute, String mentor, String mentorEmail, String mentorPhone, List<Member> members) {
        setTeamName(teamName);
        setInstitute(institute);
        setMentor(mentor);
        setMentorEmail(mentorEmail);
        setMentorPhone(mentorPhone);
        setMembers(members);

        teamNumber = null;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getInstitute() {
        return institute;
    }

    public String getMentor() {
        return mentor;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public List<Member> getMembers() {
        return members;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void AddMember(Member member) {
        members.add(member);
    }

    public int GetMemberSize() {
        return members.size();
    }

    @Override
    public String toString() {
        return "TeamModel{" +
                "teamName='" + teamName + '\'' +
                ", institute='" + institute + '\'' +
                ", mentor='" + mentor + '\'' +
                ", mentorEmail='" + mentorEmail + '\'' +
                ", mentorPhone='" + mentorPhone + '\'' +
                ", members=" + members +
                ", teamNumber='" + teamNumber + '\'' +
                '}';
    }
}
