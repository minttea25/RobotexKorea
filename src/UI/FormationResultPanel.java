package UI;

import ConstantValues.Constants;
import ConstantValues.FilePaths;
import ConstantValues.FormationOrTicket;
import ConstantValues.GUIValue;
import Model.TeamModel;
import Utils.GUIUtil;
import Utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FormationResultPanel extends JPanel {
    Map<Integer, List<TeamModel>> data;

    int numberOfShowingPanels;
    ResultPanel[] panels;

    BufferedImage contourImage;

    Set<String> loadFailSet = new HashSet<>();

    public FormationResultPanel(Map<Integer, List<TeamModel>> data) {
        this.data = data;
        this.numberOfShowingPanels = data.size();

        loadImage();

        initPanel();
        initComponents();
        attachComponents();
    }

    private void loadImage() {
        contourImage = ImageLoader.loadImage(FilePaths.VERTICAL_CONTOUR_RED_PATH);
        if (contourImage == null) {
            loadFailSet.add(FilePaths.VERTICAL_CONTOUR_RED_PATH);
        }
    }

    private void initPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(GUIValue.RESULT_PANEL_INTERVAL);
        setLayout(layout);
        setBackground(Color.white);

        GUIUtil.setSize(this,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);
    }

    private void initComponents() {
        panels = new ResultPanel[numberOfShowingPanels];

        int i=0;
        for (int key : data.keySet()) {
            panels[i] = new ResultPanel("Entry " + (key + 1), data.get(key), FormationOrTicket.Formation);
            i++;
        }
    }

    private void attachComponents() {
        for (int i=0; i<panels.length; i++) {
            add(panels[i]);

            if (contourImage != null) {
                if (i != panels.length - 1) {
                    add(new JLabel(new ImageIcon(contourImage)));
                }
            }
        }
    }

}
