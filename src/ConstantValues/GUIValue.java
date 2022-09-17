package ConstantValues;

import java.awt.*;

public class GUIValue {
    // main frame
    public final static int MAIN_WIDTH = 1920;
    public final static int MAIN_HEIGHT = 1080;

    // main panel
    public final static int MAIN_FORMATION_BUTTON_LEFT_X = 220;
    public final static int MAIN_TICKET_BUTTON_LEFT_X =  MAIN_WIDTH/2 + MAIN_FORMATION_BUTTON_LEFT_X;
    public final static int MAIN_BUTTON_Y = 820;


    // menu panel (common)
    public final static int MENU_BUTTON_INTERVAL = 100;
    public final static int MENU_BUTTON_Y = 520;
    public static final int SECTION_UP_BTN_WIDTH = 525;
    public static final int SECTION_UP_BTN_HEIGHT = 254;
    public static final int SECTION_DOWN_BTN_WIDTH = 525;
    public static final int SECTION_DOWN_BTN_HEIGHT = 255;
    public static final int BUTTON_LEFT_WIDTH = 351;
    public static final int BUTTON_LEFT_HEIGHT = 155;
    public static final int BUTTON_RIGHT_WIDTH = 174;
    public static final int BUTTON_RIGHT_HEIGHT = 155;

    // for Card Layout Name - DO NOT MODIFY THESE VALUES
    public static final String MAIN_CARD_NAME = "main";
    public static final String FORMATION_CARD_NAME = "formation";
    public static final String TICKET_CARD_NAME = "ticket";

    // back btn
    public static final int HOME_BTN_X = 50;
    public static final int HOME_BTN_Y = 60;
    public static final int HOME_BTN_WIDTH = 42;
    public static final int HOME_BTN_HEIGHT = 50;

    // for ticket menu panel
    public static final int TICKET_SECTION_BTN_WIDTH = 372;
    public static final int TICKET_SECTION_BTN_HEIGHT = 178;
    public static final int TICKET_BTN_1_X = 134;
    public static final int TICKET_BTN_Y = 645;
    public static final int TICKET_BTN_2_X = 579;
    public static final int TICKET_BTN_3_X = 1013;
    public static final int TICKET_BTN_4_X = 1437;
    public static final int TICKET_BTN_INTERVAL = 5;

    // formation / ticket frame
    public final static int RESULT_BOX_X = 690;
    public final static int RESULT_BOX_Y = 250;
    public final static int RESULT_BOX_WIDTH = 1090;
    public final static int RESULT_BOX_HEIGHT = 690;

    public final static int RESULT_PANEL_WIDTH = 420; //320
    public final static int RESULT_PANEL_HEIGHT = 670;
    public final static int RESULT_PANEL_TITLE_WIDTH = RESULT_PANEL_WIDTH;
    public final static int RESULT_PANEL_TITLE_HEIGHT = 50;
    public final static int RESULT_PANEL_BODY_WIDTH = RESULT_PANEL_WIDTH;
    public final static int RESULT_PANEL_BODY_HEIGHT = RESULT_PANEL_HEIGHT - RESULT_PANEL_TITLE_HEIGHT;
    public final static int RESULT_PANEL_INTERVAL = 20;

    public final static int TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS = 10;
    public final static int TICKET_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL = 2;
    public final static int FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL = 2;

    public final static int RESULT_PANEL_WIDTH_WQ = 700;
    public final static int RESULT_PANEL_HEIGHT_WQ = 670;

    // DO NOT MODIFY THESE VALUES
    public static final String FORMATION_BASE_CARD_NAME = "formation";

    // for ticket card layout
    public static final String TICKET_COUNT_CARD_NAME = "count";
    public static final String TICKET_CONTENT_CARD_NAME = "content";

    // for count down panel
    public static final int COUNTDOWN_LABEL_WIDTH = 400;
    public static final int COUNTDOWN_LABEL_HEIGHT = 400;

    // setup
    public final static int SETUP_MARGIN = 5;
    public final static int SETUP_GRID_PADDING = 3;
    public final static int SETUP_GRID_PADDING_LEFT = 10;
    public final static int SETUP_GRID_X_0 = 1;
    public final static int SETUP_GRID_X_1 = 2;
    public final static int SETUP_GRID_X_2 = 2;
    public final static int SETUP_GRID_Y = 1;
    public final static int SETUP_FILE_TEXT_FIELD_COLUMNS = 20;

    // font
    private static final int TITLE_FONT_SIZE = 38;
    private static final int TEXT_FONT_SIZE = 30; //23

    private static final int TEXT_FONT_SIZE_WQ = 35;

    private static final String NEXON_MAPLESTORY = "메이플스토리";

    public static final Font TITLE_FONT = new Font(NEXON_MAPLESTORY, Font.BOLD, TITLE_FONT_SIZE);
    public static final Font TEXT_FONT = new Font(NEXON_MAPLESTORY, Font.BOLD, TEXT_FONT_SIZE);

    public static final Font TEXT_FONT_WQ = new Font(NEXON_MAPLESTORY, Font.BOLD, TEXT_FONT_SIZE_WQ);

    public static final Font COUNT_FONT = new Font(NEXON_MAPLESTORY, Font.BOLD, 400);
}
