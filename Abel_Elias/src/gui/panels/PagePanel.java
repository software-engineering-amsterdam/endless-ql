package gui.panels;

import QLS.classes.Page;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PagePanel extends JPanel {
    private String key;
    private Page page;

    public PagePanel(String key, Page page) {
        this.key = key;
        this.page = page;
    }


    public Page getPage() {
        return page;
    }
}