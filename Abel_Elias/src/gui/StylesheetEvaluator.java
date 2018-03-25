package gui;

import QL.classes.values.Value;
import QLS.classes.Page;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Question;
import QLS.classes.blocks.Section;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.visitors.StylesheetVisitor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedHashMap;

public class StylesheetEvaluator {
    private StylesheetVisitor stylesheetVisitor;
    private LinkedHashMap<String, WidgetType> defaultTypes = new LinkedHashMap<>();
    private LinkedHashMap<String, JPanel> pages = new LinkedHashMap<>();
    private LinkedHashMap<String, JPanel> sections = new LinkedHashMap<>();
    private JTabbedPane tabbedPane = null;

    public StylesheetEvaluator(StylesheetVisitor stylesheetVisitor) {
        this.stylesheetVisitor = stylesheetVisitor;
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



    public JComponent buildStyleSheet() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBackground(Color.blue);
        buildPages(mainPanel);
        return mainPanel;
    }

    public void buildPages(JPanel mainPanel) {
        for (Page page : this.stylesheetVisitor.getPages().values()) {
            JPanel pagePanel = new JPanel();
            pagePanel.setLayout(new GridLayout(0, 1));

            //Set header and border
            TitledBorder border = BorderFactory.createTitledBorder(page.getId());
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
            border.setBorder(lineBorder);
            pagePanel.setBorder(border);
            mainPanel.add(pagePanel);

            buildSections(page, pagePanel);
        }
    }

    private void buildSections(Page page, JPanel pagePanel) {
        for (Section section : page.getSections()) {
            buildSection(section, pagePanel);
        }
    }

    private void buildElements(Section section, JPanel sectionPanel) {
        for (Element element : section.getElements()) {
            //TODO: replace ugly instance of statements
            if(element instanceof Question) {
                buildQuestion((Question) element, sectionPanel);
            } else if (element instanceof Section) {
                buildSection((Section) element, sectionPanel);
            }
        }
    }

    private void buildSection(Section section, JPanel parentPanel) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new GridLayout(0, 1));

        //Set header and border
        TitledBorder border = BorderFactory.createTitledBorder(section.getName());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        border.setBorder(lineBorder);
        sectionPanel.setBorder(border);
        parentPanel.add(sectionPanel);

        buildElements(section, sectionPanel);
    }

    private void buildQuestion(Question question, JPanel parentPanel) {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(0, 1));

        //Set header and border
        TitledBorder border = BorderFactory.createTitledBorder(question.getName());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        border.setBorder(lineBorder);
        questionPanel.setBorder(border);
        parentPanel.add(questionPanel);
    }


}
