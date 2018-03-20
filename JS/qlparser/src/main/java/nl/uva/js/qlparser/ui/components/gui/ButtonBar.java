package nl.uva.js.qlparser.ui.components.gui;

import javax.swing.*;
import java.awt.*;

import static nl.uva.js.qlparser.ui.GUIBuilder.*;

public class ButtonBar extends JPanel {

    public JPanel leftPanel;
    public JPanel rightPanel;
    public JPanel centerPanel;

    public ButtonBar() {
        this.setPreferredSize(new Dimension(FULL_WIDTH, BUTTON_HEIGHT));
        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        leftPanel = getButtonPanel(INPUT_WIDTH);

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(FORM_WIDTH, BUTTON_HEIGHT));
        centerPanel.setBackground(Color.GRAY);

        rightPanel = getButtonPanel(INPUT_WIDTH);

        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.LINE_END);
    }
}
