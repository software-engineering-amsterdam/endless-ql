package gui.panels;

import QLS.classes.Page;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PagePanel extends JPanel {
    private String key;
    private Page page;

    public PagePanel(String key, Page page) {
        this.key = key;
        this.page = page;

        //Set header and border
        this.setLayout(new GridLayout(0, 1));
        TitledBorder border = BorderFactory.createTitledBorder("Page: " + key);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        border.setBorder(lineBorder);
        this.setBorder(border);
    }


    public Page getPage() {
        return page;
    }
}