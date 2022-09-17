package UI;

import ConstantValues.*;
import Model.TeamModel;
import Utils.AlertDialog;
import Utils.GUIUtil;
import Utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class TicketFrame extends JFrame {
    CountDownPanel countDownPanel;
    JPanel contentPanel;

    Sections section;
    Map<Integer, List<TeamModel>> data;

    int total = 0;

    int numberOfTickets;
    int numberOfPanels;
    int numberOf3rdPanels;

    final int NUMBER_OF_TEAMS_ON_3RD_PANEL = GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS*GUIValue.TICKET_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL;

    CardLayout mainCard;

    CardLayout card;

    JLabel backgroundLabel;
    JPanel resultPanel; // card layout
    //JButton nextButton;

    TicketResultPanel[] panels;

    BufferedImage backgroundImage;

    Set<String> loadFailSet = new HashSet<>();

    public TicketFrame(Sections section, Map<Integer, List<TeamModel>> data, int numberOfTickets) {
        this.section = section;
        this.numberOfTickets = numberOfTickets;
        this.data = data;

        total = 0;
        for(List<TeamModel> l : data.values()) {
            total += l.size();
        }

        if (total > numberOfTickets*3) {
            this.numberOf3rdPanels = (int) Math.ceil(
                    (double)(total - 3*numberOfTickets)/NUMBER_OF_TEAMS_ON_3RD_PANEL);
        }
        else {
            this.numberOf3rdPanels = 0;
        }

        this.numberOfPanels = (total > numberOfTickets ? 2 : 1) + numberOf3rdPanels;

        loadImages();

        initFrame();
        initComponents();
        attachComponents();

        AlertDialog.showUnloadedImages(loadFailSet, getTicketFrame());
    }

    public void showFrame() {
        mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_COUNT_CARD_NAME);
        setVisible(true);

        countDownPanel.StartTimer();
    }

    private void initFrame() {
        mainCard = new CardLayout();
        card = new CardLayout();

        setLayout(mainCard);
        setTitle(GUIString.TICKET_FRAME_TITLE + " - " + section.toString());
        setResizable(false);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        countDownPanel = new CountDownPanel(this.getTicketFrame());
        contentPanel = new JPanel();

        contentPanel.setLayout(null);

        backgroundLabel = new JLabel();
        resultPanel = new JPanel();
        //nextButton = new JButton();
        panels = new TicketResultPanel[numberOfPanels];

        int idx = 0;
        for (int i=0; i<numberOfPanels; i++) {
            if (i==0) {
                panels[i] = new TicketResultPanel(data.get(0), 1);
            }
            else if (i==1) {
                panels[i] = new TicketResultPanel(data.get(1), 2);
            }
            else {
                List<TeamModel> t = new ArrayList<>();
                for (int j=0; j<NUMBER_OF_TEAMS_ON_3RD_PANEL; j++) {
                    if (idx >= data.get(2).size()) {
                        break;
                    }
                    t.add(data.get(2).get(idx++));
                }
                panels[i] = new TicketResultPanel(t, 3);
            }
        }

        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }

        //GUIUtil.makeButtonTransparent(nextButton);
        //nextButton.addActionListener(new NextButtonActionListener());

        resultPanel.setLayout(card);

        for (int i=0; i<numberOfPanels; i++) {
            resultPanel.add(String.valueOf(i), panels[i]);
            panels[i].addMouseListener(new NextCheckMouseListener());
        }
    }

    private void attachComponents() {
        /*contentPanel.add(nextButton);
        nextButton.setBounds(
                GUIValue.RESULT_BOX_X,
                GUIValue.RESULT_BOX_Y,
                GUIValue.RESULT_BOX_WIDTH/2, GUIValue.RESULT_BOX_HEIGHT
        );*/

        contentPanel.add(resultPanel);
        resultPanel.setBounds(
                GUIValue.RESULT_BOX_X, GUIValue.RESULT_BOX_Y,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
        );

        contentPanel.add(backgroundLabel);
        if(backgroundImage != null) {
            backgroundLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }

        add(GUIValue.TICKET_CONTENT_CARD_NAME, contentPanel);
        contentPanel.setBounds(
                0, 0,
                getWidth(), getHeight()
        );

        add(GUIValue.TICKET_COUNT_CARD_NAME, countDownPanel);
        countDownPanel.setBounds(
                0, 0,
                this.getWidth(), this.getHeight()
        );
    }

    private void showNextCard() {
        card.next(resultPanel);
    }

    private void loadImages() {
        switch (section) {
            case LegoSumo1kg : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LEGO_SUMO_1KG_BG_PATH); break;
            case LegoSumo3kg : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LEGO_SUMO_3KG_BG_PATH); break;
            case LineFollowingE : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LINE_FOLLOWING_E_BG_PATH); break;
            case LineFollowingJH : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LINE_FOLLOWING_JH_BG_PATH); break;
            case LegoFolkraceE : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LEGO_FOLKRACE_E_BG_PATH); break;
            case LegoFolkraceJH : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_LEGO_FOLKRACE_JH_BG_PATH); break;
            case RoboLeague : backgroundImage = ImageLoader.loadImage(FilePaths.TICKET_ROBO_LEAGUE_BG_PATH); break;
        }
        if (backgroundImage == null) {
            switch (section) {
                case LegoSumo1kg : loadFailSet.add(FilePaths.TICKET_LEGO_SUMO_1KG_BG_PATH); break;
                case LegoSumo3kg : loadFailSet.add(FilePaths.TICKET_LEGO_SUMO_3KG_BG_PATH); break;
                case LineFollowingE : loadFailSet.add(FilePaths.TICKET_LINE_FOLLOWING_E_BG_PATH); break;
                case LineFollowingJH : loadFailSet.add(FilePaths.TICKET_LINE_FOLLOWING_JH_BG_PATH); break;
                case LegoFolkraceE : loadFailSet.add(FilePaths.TICKET_LEGO_FOLKRACE_E_BG_PATH); break;
                case LegoFolkraceJH : loadFailSet.add(FilePaths.TICKET_LEGO_FOLKRACE_JH_BG_PATH); break;
                case RoboLeague : loadFailSet.add(FilePaths.TICKET_ROBO_LEAGUE_BG_PATH); break;
            }
        }
    }

    protected void showContentPanel() {
        mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_CONTENT_CARD_NAME);
        remove(countDownPanel);
        card.show(resultPanel, "0");
    }

    private TicketFrame getTicketFrame() {
        return this;
    }

    class GifCloseThread extends Thread {

        @Override
        public void run() {
            try {
                mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_COUNT_CARD_NAME);
                Thread.sleep(Constants.COUNTDOWN_CLOSE_TIME);
                mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_CONTENT_CARD_NAME);
                remove(countDownPanel);

                card.show(resultPanel, String.valueOf(0));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*class NextButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == nextButton) {
                showNextCard();
            }
        }
    }*/

    class NextCheckMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            showNextCard();
            /*int x = e.getX();
            int y = e.getY();
            System.out.println("Clicked: " + x + " " + y);
            if (x>=GUIValue.RESULT_BOX_X && x <= GUIValue.RESULT_BOX_X+GUIValue.RESULT_BOX_WIDTH
                && y >= GUIValue.RESULT_BOX_Y && y <= GUIValue.RESULT_BOX_Y+GUIValue.RESULT_BOX_WIDTH) {
                showNextCard();
            }*/
        }
    }
}
