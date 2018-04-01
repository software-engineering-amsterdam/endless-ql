package gui;

import QL.parsing.visitors.FormVisitor;
import QLS.classes.Page;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.classes.properties.ColorProperty;
import QLS.classes.properties.Property;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.panels.PagePanel;
import gui.panels.QuestionPanel;
import gui.panels.SectionPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

public class QLSBuilder implements Observer {
    private StylesheetVisitor stylesheetVisitor;
    private FormVisitor coreVisitor;

    private LinkedHashMap<String, PagePanel> pages = new LinkedHashMap<>();
    private ArrayList<QuestionPanel> questionPanels;
    private JPanel mainPanel;

    public QLSBuilder(StylesheetVisitor stylesheetVisitor, FormVisitor coreVisitor) {
        this.stylesheetVisitor = stylesheetVisitor;
        this.coreVisitor = coreVisitor;

        this.questionPanels = new ArrayList<>();

        this.mainPanel = new JPanel();
        buildStyleSheet();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void buildStyleSheet() {
        buildPages();
        createStyledForm();
    }

    private void buildPages() {
        for (Page page : this.stylesheetVisitor.getPages().values()) {
            PagePanel pagePanel = new PagePanel(page.getId(), page);
            for (Section section : page.getSections()) {
                pagePanel.add(buildSection(section));
            }
            mainPanel.add(pagePanel);
        }
    }

    private void buildElements(SectionPanel sectionPanel) {
        for (Element element : sectionPanel.getSection().getElements()) {
            //TODO: replace ugly instance of statements
            if (element instanceof StyledQuestion) {
                sectionPanel.add(buildQuestion((StyledQuestion) element));
            } else if (element instanceof Section) {
                sectionPanel.add(buildSection((Section) element));
            }
        }
    }

    private SectionPanel buildSection(Section section) {
        SectionPanel sectionPanel = new SectionPanel(section);
        buildElements(sectionPanel);
        return sectionPanel;
    }

    private QuestionPanel buildQuestion(StyledQuestion styledQuestion) {
        if (!styledQuestion.getQuestion().isFixed()) {
            styledQuestion.getQuestion().getValue().addObserver(this);
        }
        QuestionPanel questionPanel = new QuestionPanel(styledQuestion.getQuestion(), styledQuestion.getWidget());
        for(Property property : styledQuestion.getProperties()) {
            if(property != null) {
                property.applyProperty(questionPanel);
            }
        }

        questionPanels.add(questionPanel);
        return questionPanel;
    }

    private void createStyledForm() {
        this.updateGUI();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateGUI() {
        //Iterate over the total question hashmap
        for (QuestionPanel q : questionPanels) {
            if (q.getQuestion().isVisible()) {
                q.setVisible(true);
                if (q.getQuestion().isFixed()) {
                    q.refresh();
                }
            } else {
                q.setVisible(false);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.coreVisitor.update();
        this.updateGUI();

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}