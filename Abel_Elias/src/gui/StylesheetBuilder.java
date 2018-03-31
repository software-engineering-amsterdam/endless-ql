package gui;

import QL.classes.values.Value;
import QLS.classes.Page;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.questions.QuestionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedHashMap;

public class StylesheetBuilder {
    private StylesheetVisitor stylesheetVisitor;
    private LinkedHashMap<String, JPanel> styledQuestions = new LinkedHashMap<>();

    public StylesheetBuilder(StylesheetVisitor stylesheetVisitor) {
        this.stylesheetVisitor = stylesheetVisitor;
    }

    public JComponent buildStyleSheet() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
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
            if(element instanceof StyledQuestion) {
                buildQuestion((StyledQuestion) element, sectionPanel);
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

    private void buildQuestion(StyledQuestion question, JPanel parentPanel) {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(0, 1));

        if(styledQuestions.containsKey(question.getQuestion().getId())) {
            parentPanel.add(styledQuestions.get(question.getQuestion().getId()));
        }
    }


    public void setWidget(QuestionPanel widget) {
        styledQuestions.put(widget.getQuestion().getId(), widget);
    }
}
