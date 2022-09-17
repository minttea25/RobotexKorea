package Model;

import ConstantValues.FormationOrTicket;
import ConstantValues.Sections;

public class SetupDataModel {
    int Formation_LegoSumo1kg;
    int Formation_LegoSumo3kg;
    int Formation_LineFollowingE;
    int Formation_LineFollowingJH;
    int Formation_FolkraceE;
    int Formation_FolkraceJH;

    int Ticket_LegoSumo1kg;
    int Ticket_LegoSumo3kg;
    int Ticket_LineFollowingE;
    int Ticket_LineFollowingJH;
    int Ticket_FolkraceE;
    int Ticket_FolkraceJH;

    int Ticket_RoboLeague;

    public SetupDataModel(){}

    public void setFormation_FolkraceE(int formation_FolkraceE) {
        Formation_FolkraceE = formation_FolkraceE;
    }

    public void setFormation_FolkraceJH(int formation_FolkraceJH) {
        Formation_FolkraceJH = formation_FolkraceJH;
    }

    public void setFormation_LegoSumo1kg(int formation_LegoSumo1kg) {
        Formation_LegoSumo1kg = formation_LegoSumo1kg;
    }

    public void setFormation_LegoSumo3kg(int formation_LegoSumo3kg) {
        Formation_LegoSumo3kg = formation_LegoSumo3kg;
    }

    public void setFormation_LineFollowingE(int formation_LineFollowingE) {
        Formation_LineFollowingE = formation_LineFollowingE;
    }

    public void setFormation_LineFollowingJH(int formation_LineFollowingJH) {
        Formation_LineFollowingJH = formation_LineFollowingJH;
    }

    public void setTicket_FolkraceE(int ticket_FolkraceE) {
        Ticket_FolkraceE = ticket_FolkraceE;
    }

    public void setTicket_FolkraceJH(int ticket_FolkraceJH) {
        Ticket_FolkraceJH = ticket_FolkraceJH;
    }

    public void setTicket_LegoSumo1kg(int ticket_LegoSumo1kg) {
        Ticket_LegoSumo1kg = ticket_LegoSumo1kg;
    }

    public void setTicket_LegoSumo3kg(int ticket_LegoSumo3kg) {
        Ticket_LegoSumo3kg = ticket_LegoSumo3kg;
    }

    public void setTicket_LineFollowingE(int ticket_LineFollowingE) {
        Ticket_LineFollowingE = ticket_LineFollowingE;
    }

    public void setTicket_LineFollowingJH(int ticket_LineFollowingJH) {
        Ticket_LineFollowingJH = ticket_LineFollowingJH;
    }

    public void setTicket_RoboLeague(int ticket_RoboLeague) {
        Ticket_RoboLeague = ticket_RoboLeague;
    }

    public int getFormation_FolkraceE() {
        return Formation_FolkraceE;
    }

    public int getFormation_FolkraceJH() {
        return Formation_FolkraceJH;
    }

    public int getFormation_LegoSumo1kg() {
        return Formation_LegoSumo1kg;
    }

    public int getFormation_LegoSumo3kg() {
        return Formation_LegoSumo3kg;
    }

    public int getFormation_LineFollowingE() {
        return Formation_LineFollowingE;
    }

    public int getFormation_LineFollowingJH() {
        return Formation_LineFollowingJH;
    }

    public int getTicket_FolkraceE() {
        return Ticket_FolkraceE;
    }

    public int getTicket_FolkraceJH() {
        return Ticket_FolkraceJH;
    }

    public int getTicket_LegoSumo1kg() {
        return Ticket_LegoSumo1kg;
    }

    public int getTicket_LegoSumo3kg() {
        return Ticket_LegoSumo3kg;
    }

    public int getTicket_LineFollowingE() {
        return Ticket_LineFollowingE;
    }

    public int getTicket_LineFollowingJH() {
        return Ticket_LineFollowingJH;
    }

    public int getTicket_RoboLeague() {
        return Ticket_RoboLeague;
    }

    public int getValue(FormationOrTicket fun, Sections s) {
        if (fun == FormationOrTicket.Formation) {
            switch (s) {
                case LegoSumo1kg : return this.Formation_LegoSumo1kg;
                case LegoSumo3kg : return this.Formation_LegoSumo3kg;
                case LineFollowingE : return this.Formation_LineFollowingE;
                case LineFollowingJH : return this.Formation_LineFollowingJH;
                case LegoFolkraceE : return this.Formation_FolkraceE;
                case LegoFolkraceJH : return this.Formation_FolkraceJH;
                default: return -1;
            }
        }
        else if (fun == FormationOrTicket.Ticket) {
            switch (s) {
                case LegoSumo1kg : return this.Ticket_LegoSumo1kg;
                case LegoSumo3kg : return this.Ticket_LegoSumo3kg;
                case LineFollowingE : return this.Ticket_LineFollowingE;
                case LineFollowingJH : return this.Ticket_LineFollowingJH;
                case LegoFolkraceE : return this.Ticket_FolkraceE;
                case LegoFolkraceJH : return this.Ticket_FolkraceJH;
                case RoboLeague : return this.Ticket_RoboLeague;
                default: return -1;
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        return "SetupDataModel{" +
                "Formation_LegoSumo1kg=" + Formation_LegoSumo1kg +
                ", Formation_LegoSumo3kg=" + Formation_LegoSumo3kg +
                ", Formation_LineFollowingE=" + Formation_LineFollowingE +
                ", Formation_LineFollowingJH=" + Formation_LineFollowingJH +
                ", Formation_FolkraceE=" + Formation_FolkraceE +
                ", Formation_FolkraceJH=" + Formation_FolkraceJH +
                ", Ticket_LegoSumo1kg=" + Ticket_LegoSumo1kg +
                ", Ticket_LegoSumo3kg=" + Ticket_LegoSumo3kg +
                ", Ticket_LineFollowingE=" + Ticket_LineFollowingE +
                ", Ticket_LineFollowingJH=" + Ticket_LineFollowingJH +
                ", Ticket_FolkraceE=" + Ticket_FolkraceE +
                ", Ticket_FolkraceJH=" + Ticket_FolkraceJH +
                ", Ticket_RoboLeague=" + Ticket_RoboLeague +
                '}';
    }
}
