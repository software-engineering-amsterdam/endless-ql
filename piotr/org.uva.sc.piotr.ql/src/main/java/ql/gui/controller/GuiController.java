package ql.gui.controller;

import ql.gui.view.WindowView;
import ql.gui.view.panels.OpeningPanel;

import javax.swing.*;

public class GuiController {
    public GuiController() {
        WindowView windowView = new WindowView("QL Form GUI");
        JPanel openingPanel = new OpeningPanel(windowView);
        windowView.setMainPanel(openingPanel);
        windowView.formatAndShow();
    }
}
