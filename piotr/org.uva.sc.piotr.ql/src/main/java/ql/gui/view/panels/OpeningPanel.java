package ql.gui.view.panels;

import ql.gui.controller.OpenFileController;
import ql.gui.view.WindowView;

import javax.swing.*;
import java.awt.*;

public class OpeningPanel extends JPanel {

    public OpeningPanel(WindowView windowView) {
        this.setPreferredSize(new Dimension(200, 200));
        JButton openingButton = new JButton("Open file...");
        this.add(openingButton);
        openingButton.addActionListener(new OpenFileController(windowView));
    }
}
