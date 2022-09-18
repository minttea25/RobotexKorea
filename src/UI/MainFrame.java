package UI;

import ConstantValues.*;
import Setup.Setup;
import Utils.AlertDialog;
import Utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CardLayout card;

    private MainPanel mainPanel;
    private MenuPanel formationPanel;
    private MenuPanel ticketPanel;

    public MainFrame() {
        initFrame();
        initComponents();
        attachComponents();
    }

    public void showFrame() {
        card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
        setVisible(true);
    }

    private void attachComponents() {
        add(GUIValue.MAIN_CARD_NAME, mainPanel);
        add(GUIValue.FORMATION_CARD_NAME, formationPanel);
        add(GUIValue.TICKET_CARD_NAME, ticketPanel);
    }

    private void initFrame() {
        card = new CardLayout();

        setLayout(card);
        setTitle(GUIString.MAIN_FRAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        initMainPanel();
        initFormationPanel();
        initTicketPanel();
    }

    private void initMainPanel() {
        mainPanel = new MainPanel();
        mainPanel.addButtonActionListener(new MainPanelActionListener());
    }

    private void initFormationPanel() {
        formationPanel = new MenuPanel(FormationOrTicket.Formation);
        formationPanel.addButtonActionListener(new FormationPanelActionListener());
    }

    private void initTicketPanel() {
        ticketPanel = new MenuPanel(FormationOrTicket.Ticket);
        ticketPanel.addButtonActionListener(new TicketPanelActionListener());
    }

    private JFrame getMainFrame() {
        return this;
    }

    class MainPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            // 설정 파일 못 찾았을 때
            if (!Setup.getInstance().isCannotFindFile()) {
                AlertDialog.showAlertDialog(getMainFrame(), ErrorCode.Cannot_Find_File.msg + FilePaths.SETTING_FILE_PATH , ErrorCode.Cannot_Find_File.code);
                return;
            }

            if (obj == mainPanel.formationSetupBtn || obj == mainPanel.ticketSetupBtn) {
                // setup dialog 띄워주기
                SetupDialog dialog = new SetupDialog(getMainFrame());
                dialog.showDialog();
            }
            // 엑셀 파일이 아직 로드 되지 않았을 때
            else if (!Setup.getInstance().isRobotexLoaded()) {
                JOptionPane.showConfirmDialog(
                        getMainFrame(),
                        ErrorCode.Excel_Not_Loaded.msg,
                        ErrorCode.Excel_Not_Loaded.code,
                        JOptionPane.DEFAULT_OPTION
                );
            }
            else if (obj == mainPanel.formationBtn) {
                card.show(getContentPane(), GUIValue.FORMATION_CARD_NAME);
            }
            else if (obj == mainPanel.ticketBtn) {
                card.show(getContentPane(), GUIValue.TICKET_CARD_NAME);
            }
        }
    }

    class FormationPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            Sections s = null;

            if (obj == formationPanel.legoSumo1kgBtn) {
                s = Sections.LegoSumo1kg;
            }
            else if (obj == formationPanel.legoSumo3kgBtn) {
                s = Sections.LegoSumo3kg;
            }
            else if (obj == formationPanel.lineFollowingEBtn) {
                s = Sections.LineFollowingE;
            }
            else if (obj == formationPanel.lineFollowingJHBtn) {
                s = Sections.LineFollowingJH;
            }
            else if (obj == formationPanel.legoFolkraceEBtn) {
                s = Sections.LegoFolkraceE;
            }
            else if (obj == formationPanel.legoFolkraceJHBtn) {
                s = Sections.LegoFolkraceJH;
            }
            else if (obj == formationPanel.backBtn) {
                card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
                return;
            }

            if (s != null) {
                if (!checkStatus(FormationOrTicket.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        Setup.getInstance().getEntryMaps().get(s)
                );

                frame.showFrame();
            }
        }
    }

    class TicketPanelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            Sections s = null;

            if (obj == ticketPanel.legoSumo1kgBtn) {
                s = Sections.LegoSumo1kg;
            }
            else if (obj == ticketPanel.legoSumo3kgBtn) {
                s = Sections.LegoSumo3kg;
            }
            else if (obj == ticketPanel.lineFollowingEBtn) {
                s = Sections.LineFollowingE;
            }
            else if (obj == ticketPanel.lineFollowingJHBtn) {
                s = Sections.LineFollowingJH;
            }
            else if (obj == ticketPanel.legoFolkraceEBtn) {
                s = Sections.LegoFolkraceE;
            }
            else if (obj == ticketPanel.legoFolkraceJHBtn) {
                s = Sections.LegoFolkraceJH;
            }
            else if (obj == ticketPanel.roboLeagueBtn1 || obj == ticketPanel.roboLeagueBtn2) {
                s = Sections.RoboLeague;
            }
            else if (obj == ticketPanel.backBtn) {
                card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
                return;
            }

            if (s != null) {
                if (!checkStatus(FormationOrTicket.Ticket, s)) {
                    return;
                }
                TicketFrame frame = new TicketFrame(
                        s,
                        Setup.getInstance().getTicketMaps().get(s),
                        Setup.getInstance().getSetupData().getValue(FormationOrTicket.Ticket, s)
                );
                frame.showFrame();
            }
        }
    }

    private boolean checkStatus(FormationOrTicket fun, Sections s) {
        if(fun == FormationOrTicket.Formation) {
            // not load data
            if (!Setup.getInstance().getStatus().get(s)) {
                AlertDialog.showAlertDialog(getMainFrame(), ErrorCode.Sheet_Not_Loaded.msg + s, ErrorCode.Sheet_Not_Loaded.code);
                return false;
            }
            // wrong num
            else if (!Setup.getInstance().getStatus().get(s)) {
                String msg = ErrorCode.Wrong_Number_Error.msg + ": " + s + "\ndata size: " + Setup.getInstance().getTeamDataBySection(s).size()
                        + "\nnow: " + Setup.getInstance().getSetupData().getValue(FormationOrTicket.Formation, s);
                AlertDialog.showAlertDialog(getMainFrame(), msg, ErrorCode.Wrong_Number_Error.code);
                return false;
            }
        }
        else {
            // not load data
            if (!Setup.getInstance().getStatus().get(s)) {
                AlertDialog.showAlertDialog(getMainFrame(), ErrorCode.Sheet_Not_Loaded.msg + s, ErrorCode.Sheet_Not_Loaded.code);
                return false;
            }
            // wrong num
            else if (!Setup.getInstance().getStatus().get(s)) {
                String msg = ErrorCode.Wrong_Number_Error.msg + ": " + s + "\ndata size: " + Setup.getInstance().getTeamDataBySection(s).size()
                        + "\nnow: " + Setup.getInstance().getSetupData().getValue(FormationOrTicket.Ticket, s);
                AlertDialog.showAlertDialog(getMainFrame(), msg, ErrorCode.Wrong_Number_Error.code);
                return false;
            }
        }

        return true;
    }
}
