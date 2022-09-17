package UI;

import ConstantValues.*;
import Setup.Setup;
import Utils.AlertDialog;
import Utils.ExcelFileChooser;
import Utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SetupDialog extends JDialog{
    Component parent;

    JPanel filePanel;
    JLabel labelFile;
    JButton buttonFile;
    JTextField textFieldFile;

    JPanel valuePanel;
    JLabel labelNumberOfEntries;
    JLabel labelNumberOfTickets;

    JLabel labelLegoSumo1kg;
    JLabel labelLegoSumo3kg;
    JLabel labelLineFollowingE;
    JLabel labelLineFollowingJH;
    JLabel labelFolkraceE;
    JLabel labelFolkraceJH;
    JLabel labelRoboleague;

    JLabel labelFormationLegoSumo1kg;
    JLabel labelFormationLegoSumo3kg;
    JLabel labelFormationLineFollowingE;
    JLabel labelFormationLineFollowingJH;
    JLabel labelFormationFolkraceE;
    JLabel labelFormationFolkraceJH;
    JLabel labelFormationRoboleague;

    JLabel labelTicketLegoSumo1kg;
    JLabel labelTicketLegoSumo3kg;
    JLabel labelTicketLineFollowingE;
    JLabel labelTicketLineFollowingJH;
    JLabel labelTicketFolkraceE;
    JLabel labelTicketFolkraceJH;
    JLabel labelTicketRoboleague;

    JPanel buttonPanel;
    JButton btnExcelLoad;
    JButton btnOK;

    String filePath = null;

    public SetupDialog(Component parent) {
        this.parent = parent;

        initDialog();
        initComponents();

        attachComponents();
    }

    private void attachComponents() {
        add(filePanel, BorderLayout.NORTH);
        add(valuePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    public void showDialog() {
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initComponents() {
        filePanel = new JPanel();
        labelFile = new JLabel(GUIString.FILE_LABEL);
        textFieldFile = new JTextField(GUIValue.SETUP_FILE_TEXT_FIELD_COLUMNS);
        buttonFile = new JButton(GUIString.CHOOSE_FILE);

        valuePanel = new JPanel();
        labelNumberOfEntries = new JLabel(GUIString.ENTRY_NUMBER);
        labelNumberOfTickets = new JLabel(GUIString.TICKETS_NUMBER);

        labelLegoSumo1kg = new JLabel(Sections.LegoSumo1kg.toString());
        labelLegoSumo3kg = new JLabel(Sections.LegoSumo3kg.toString());
        labelLineFollowingE = new JLabel(Sections.LineFollowingE.toString());
        labelLineFollowingJH = new JLabel(Sections.LineFollowingJH.toString());
        labelFolkraceE = new JLabel(Sections.LegoFolkraceE.toString());
        labelFolkraceJH = new JLabel(Sections.LegoFolkraceJH.toString());
        labelRoboleague = new JLabel(Sections.RoboLeague.toString());

        labelFormationLegoSumo1kg = new JLabel("-");
        labelFormationLegoSumo3kg = new JLabel("-");
        labelFormationLineFollowingE = new JLabel("-");
        labelFormationLineFollowingJH = new JLabel("-");
        labelFormationFolkraceE = new JLabel("-");
        labelFormationFolkraceJH = new JLabel("-");
        labelFormationRoboleague = new JLabel("-");

        labelTicketLegoSumo1kg = new JLabel("-");
        labelTicketLegoSumo3kg = new JLabel("-");
        labelTicketLineFollowingE = new JLabel("-");
        labelTicketLineFollowingJH = new JLabel("-");
        labelTicketFolkraceE = new JLabel("-");
        labelTicketFolkraceJH = new JLabel("-");
        labelTicketRoboleague = new JLabel("-");

        buttonPanel = new JPanel();
        btnExcelLoad = new JButton(GUIString.LOAD);
        btnOK = new JButton(GUIString.OK);

        labelNumberOfEntries.setHorizontalAlignment(JLabel.CENTER);

        labelFormationLegoSumo1kg.setHorizontalAlignment(JLabel.CENTER);
        labelFormationLegoSumo3kg.setHorizontalAlignment(JLabel.CENTER);
        labelFormationLineFollowingE.setHorizontalAlignment(JLabel.CENTER);
        labelFormationLineFollowingJH.setHorizontalAlignment(JLabel.CENTER);
        labelFormationFolkraceE.setHorizontalAlignment(JLabel.CENTER);
        labelFormationFolkraceJH.setHorizontalAlignment(JLabel.CENTER);
        labelFormationRoboleague.setHorizontalAlignment(JLabel.CENTER);

        labelTicketLegoSumo1kg.setHorizontalAlignment(JLabel.CENTER);
        labelTicketLegoSumo3kg.setHorizontalAlignment(JLabel.CENTER);
        labelTicketLineFollowingE.setHorizontalAlignment(JLabel.CENTER);
        labelTicketLineFollowingJH.setHorizontalAlignment(JLabel.CENTER);
        labelTicketFolkraceE.setHorizontalAlignment(JLabel.CENTER);
        labelTicketFolkraceJH.setHorizontalAlignment(JLabel.CENTER);
        labelTicketRoboleague.setHorizontalAlignment(JLabel.CENTER);

        btnExcelLoad.setToolTipText(GUIString.SELECT_FILE_FIRST_TOOLTIP);
        btnOK.setToolTipText(GUIString.LOAD_FIRST_TOOLTIP);

        initFilePanel();
        initValuePanel();
        initButtonPanel();
    }

    private void initValuePanel() {
        valuePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.top = GUIValue.SETUP_GRID_PADDING;
        gbc.insets.bottom = GUIValue.SETUP_GRID_PADDING;
        gbc.insets.left = GUIValue.SETUP_GRID_PADDING_LEFT;
        gbc.insets.right = GUIValue.SETUP_GRID_PADDING;

        GUIUtil.setGridBagConstraintsWeight(gbc, 1.0, 1.0);

        int[] t_width = {
                GUIValue.SETUP_GRID_X_0, GUIValue.SETUP_GRID_X_1, GUIValue.SETUP_GRID_X_2
        };

        makeRowGridConstraints(gbc,
                0,
                valuePanel, t_width,
                new JPanel(), labelNumberOfEntries, labelNumberOfTickets);

        makeRowGridConstraints(gbc,
                1,
                valuePanel, t_width,
                labelLegoSumo1kg, labelFormationLegoSumo1kg, labelTicketLegoSumo1kg);

        makeRowGridConstraints(gbc,
                2,
                valuePanel, t_width,
                labelLegoSumo3kg, labelFormationLegoSumo3kg, labelTicketLegoSumo3kg);

        makeRowGridConstraints(gbc,
                3,
                valuePanel, t_width,
                labelLineFollowingE, labelFormationLineFollowingE, labelTicketLineFollowingE);

        makeRowGridConstraints(gbc,
                4,
                valuePanel, t_width,
                labelLineFollowingJH, labelFormationLineFollowingJH, labelTicketLineFollowingJH);

        makeRowGridConstraints(gbc,
                5,
                valuePanel,t_width,
                labelFolkraceE, labelFormationFolkraceE, labelTicketFolkraceE);

        makeRowGridConstraints(gbc,
                6,
                valuePanel, t_width,
                labelFolkraceJH, labelFormationFolkraceJH, labelTicketFolkraceJH);

        makeRowGridConstraints(gbc,
                7,
                valuePanel, t_width,
                labelRoboleague, labelFormationRoboleague, labelTicketRoboleague);
    }

    private void initButtonPanel() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(btnExcelLoad);

        btnOK.setEnabled(false);
        buttonPanel.add(btnOK);

        btnExcelLoad.addActionListener(new ButtonEventListener());
        btnOK.addActionListener(new ButtonEventListener());
    }

    private void makeRowGridConstraints(GridBagConstraints gbc, int gridy, JPanel panel, int[] width, Component... components) {
        if (width.length != components.length) {
            return;
        }

        int gridx = 0;
        for (int i=0; i<components.length; i++) {
            GUIUtil.setGridBagConstraints(gbc,
                    gridx , gridy,
                    width[i], GUIValue.SETUP_GRID_Y);
            panel.add(components[i], gbc);

            gridx += width[i];
        }

    }

    private void initFilePanel() {
        filePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GUIUtil.setPanelMargin(filePanel, GUIValue.SETUP_MARGIN);

        labelFile.setHorizontalAlignment(JLabel.CENTER);
        filePanel.add(labelFile);

        textFieldFile.setEditable(false);
        filePanel.add(textFieldFile);

        buttonFile.addActionListener(new ButtonEventListener());
        filePanel.add(buttonFile);
    }

    private void initDialog() {
        setTitle(GUIString.FORMATION_SETUP_TITLE);

        setAlwaysOnTop(true);
        setLayout(new BorderLayout(GUIValue.SETUP_MARGIN, GUIValue.SETUP_MARGIN));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == buttonFile) {
                selectFile();
            }
            else if (obj == btnExcelLoad) {
                if (getFilePath() == null || Objects.equals(getFilePath(), "")) {
                    //System.out.println("There is no selected file");
                    AlertDialog.showAlertDialog(getSetupDialog(), ErrorCode.No_File_Selected.msg, ErrorCode.No_File_Selected.code);
                    return;
                }

                Setup.getInstance().loadSetup(getFilePath());
                if (Setup.getInstance().isLoaded()) {
                    StringBuilder msg = new StringBuilder(GUIString.EXCEL_LOAD_MSG + getFilePath());
                    for (Sections s : Setup.getInstance().getStatus().keySet()) {
                        if (Setup.getInstance().getStatus().get(s)) {
                            msg.append("\n").append(s.toString());
                        }
                    }

                    //System.out.println("Load Success!");
                    JOptionPane.showMessageDialog(
                            getSetupDialog(),
                            msg.toString(),
                            GUIString.NOTICE,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    btnOK.setEnabled(true);
                    btnOK.setToolTipText(null);
                    btnExcelLoad.setText(GUIString.LOADED);
                    btnExcelLoad.setEnabled(false);
                }
                else {
                    AlertDialog.showAlertDialog(getSetupDialog(), ErrorCode.Robotex_Sheet_Not_Loaded.msg, ErrorCode.Robotex_Sheet_Not_Loaded.code);
                }
            }
            else if (obj == btnOK) {
                if (!Setup.getInstance().checkSetupValues(FormationOrTicket.Formation)) {
                    String msg = ErrorCode.Wrong_Number_Error.msg + "\n" + Setup.getInstance().getCheckNumErrorList();
                    AlertDialog.showAlertDialog(getSetupDialog(), msg, ErrorCode.Wrong_Number_Error.code);
                }

                Setup.getInstance().saveFiles();

                boolean atLeast = false;
                String txt = GUIString.SECTION_LOAD_SUCCESS_MSG;
                for (Sections s : Setup.getInstance().getWriteStatus().keySet()) {
                    if (Setup.getInstance().getWriteStatus().get(s)) {
                        txt += "\n " + s.toString();
                        atLeast = true;
                    }
                }
                int r;
                if (atLeast) {
                    r = AlertDialog.showAlertDialogWait(getSetupDialog(), txt, GUIString.NOTICE);
                }
                else {
                    r = AlertDialog.showAlertDialogWait(getSetupDialog(), txt + "\n\nNONE", GUIString.NOTICE);
                }
                if (r == JOptionPane.OK_OPTION) {
                    dispose();
                }
            }
        }
    }

    private void selectFile() {
        ExcelFileChooser chooser = new ExcelFileChooser(this);
        chooser.openDialog();
        String path = chooser.getFilePath();
        if (path != null) {
            filePath = path;
            textFieldFile.setText(path);
            btnExcelLoad.setToolTipText(null);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public SetupDialog getSetupDialog() {
        return this;
    }
}
