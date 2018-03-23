package gui;

import QL.classes.values.Value;
import QLS.classes.Page;
import QLS.classes.Stylesheet;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Question;
import QLS.classes.blocks.Section;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.WidgetType;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedHashMap;

public class StylesheetEvaluator {
    private Stylesheet stylesheet;
    private LinkedHashMap<String, WidgetType> defaultTypes = new LinkedHashMap<>();
    private LinkedHashMap<String, JPanel> pages = new LinkedHashMap<>();
    private LinkedHashMap<String, JPanel> sections = new LinkedHashMap<>();

    public StylesheetEvaluator() {
        setDefaults();
    }

    private void setDefaults() {
        setDefaultWidgetTypes();
    }

    private void setDefaultWidgetTypes() {
        defaultTypes.put(Value.STRING, new TextWidget());
        defaultTypes.put(Value.MONEY, new TextWidget());
        defaultTypes.put(Value.INTEGER, new TextWidget());
        defaultTypes.put(Value.BOOLEAN, new CheckBoxWidget());
    }

    public void buildPages() {
        for (Page page : this.stylesheet.getPages()) {
            JPanel pagePanel = new JPanel();
            pagePanel.setLayout(new GridLayout(0, 1));
            pages.put(page.getId(), pagePanel);

            //Build sections
//            for (Block block : page.getSections()) {
//                for(Element element : block.getBlockElements()) {
//                    if(element instanceof Section) {
//                        Section section = (Section) element;
//                        JPanel sectionPanel = new JPanel();
//                        sectionPanel.setLayout(new GridLayout(0, 1));
//                        TitledBorder border = BorderFactory.createTitledBorder(section.getName());
//                        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
//                        border.setBorder(lineBorder);
//                        sectionPanel.setBorder(border);
//                        sections.put(section.getName(), sectionPanel);
//                    }
//                }
//            }

        }
    }
}
