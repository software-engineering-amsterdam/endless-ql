package gui.panels;

import QLS.classes.blocks.Section;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SectionPanel extends JPanel {
    private Section section;

    public SectionPanel(Section section) {
        this.section = section;

        this.setLayout(new GridLayout(0, 1));

        //Set header and border
        TitledBorder border = BorderFactory.createTitledBorder(section.getName());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        border.setBorder(lineBorder);
        this.setBorder(border);
    }


    public Section getSection() {
        return this.section;
    }
}