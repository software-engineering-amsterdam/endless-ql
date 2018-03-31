package gui;

import QL.classes.Question;
import QL.classes.values.Value;
import QLS.classes.Page;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.questions.PagePanel;
import gui.questions.QuestionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class QLSBuilder {
    private StylesheetVisitor stylesheetVisitor;
    private LinkedHashMap<StyledQuestion, JPanel> styledQuestions = new LinkedHashMap<>();
    private LinkedHashMap<String, PagePanel> pages = new LinkedHashMap<>();
    private LinkedHashMap<String, JPanel> sections = new LinkedHashMap<>();

    public JPanel getStyleSheetPanel() {
        return styleSheetPanel;
    }

    private JPanel styleSheetPanel;


    public QLSBuilder(StylesheetVisitor stylesheetVisitor) {
        this.stylesheetVisitor = stylesheetVisitor;
        styleSheetPanel = new JPanel();
        styleSheetPanel.setLayout(new GridLayout(0, 1));
        buildStyleSheet();
    }

    public void buildStyleSheet() {
        buildPages();
    }

    public void buildPages() {
        for (Page page : this.stylesheetVisitor.getPages().values()) {
            PagePanel pagePanel = new PagePanel(page.getId(), page);
            pagePanel.setLayout(new GridLayout(0, 1));

            //Set header and border
            TitledBorder border = BorderFactory.createTitledBorder(page.getId());
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
            border.setBorder(lineBorder);
            pagePanel.setBorder(border);
            pages.put(page.getId(), pagePanel);
            //styleSheetPanel.add(pagePanel);
            buildSections(page);
        }
    }

    private void buildSections(Page page) {
        for (Section section : page.getSections()) {
            buildSection(section);
        }
    }

    private void buildElements(Section section) {
        for (Element element : section.getElements()) {
            //TODO: replace ugly instance of statements
            if(element instanceof StyledQuestion) {
                buildQuestion((StyledQuestion) element);
            } else if (element instanceof Section) {
                buildSection((Section) element);
            }
        }
    }

    private void buildSection(Section section) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new GridLayout(0, 1));

        //Set header and border
        TitledBorder border = BorderFactory.createTitledBorder(section.getName());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        border.setBorder(lineBorder);
        sectionPanel.setBorder(border);
        sections.put(section.getName(), sectionPanel);
        buildElements(section);
    }

    private void buildQuestion(StyledQuestion question) {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(0, 1));
        styledQuestions.put(question, questionPanel);
    }


    public void setWidgets(LinkedHashMap<String, QuestionPanel> formQuestions) {

        //Remove all existing section panel content
        for(JPanel panel : sections.values()) {
            panel.removeAll();
        }

        //Add all questions to their parent section
        for (StyledQuestion styledQuestion : styledQuestions.keySet()) {
            String parentId = styledQuestion.getParentId();
            if (parentId != null && sections.containsKey(parentId)) {
                JPanel sectionPanel = sections.get(parentId);
                QuestionPanel panel = formQuestions.get(styledQuestion.getQuestion().getId());
                if(panel != null) {
                    sectionPanel.add(panel);
                }
            }
        }
        for(JPanel section : sections.values()) {
            styleSheetPanel.add(section);
        }

//        for(PagePanel pagePanel : pages.values()) {
//            for (Map.Entry<String, JPanel> entry : sections.entrySet()) {
//                for(Section section :pagePanel.getPage().getSections()) {
//                    if(section.getName().equals(entry.getKey())) {
//                        pagePanel.add(entry.getValue());
//                    }
//                }
//            }
//            styleSheetPanel.add(pagePanel);
//        }
        styleSheetPanel.revalidate();
        styleSheetPanel.repaint();
    }
}
