package org.uva.qls.evaluator;

import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.StringType;
import org.uva.qls.ast.Segment.*;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.WidgetTypes.CheckboxType;
import org.uva.qls.ast.Widget.WidgetTypes.TextType;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;
import org.uva.qls.collector.StylesheetContext;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleEvaluator {

    private Stylesheet stylesheet;
    private StylesheetContext context;

    private Map<String, WidgetType> defaultTypes = new HashMap<>();

    private Map<String, JPanel> sections = new HashMap<>();
    private List<String> visibleSections = new ArrayList<>();

    public StyleEvaluator() {
        setDefaultWidgetTypes();
        setDefaultSection();
    }

    public void setStylesheet(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
        this.context = new StylesheetContext(stylesheet);
        generateSections();
    }

    public void setWidget(QuestionReference questionReference, JPanel widget) {
        sections.put(questionReference.getId(), widget);
    }

    public void setVisible(QuestionReference questionReference) {
        String key = questionReference.getId();
        visibleSections.add(key);
        for (Segment segment : context.getAllParents(key)) {
            visibleSections.add(segment.getId());
        }
    }

    public QuestionReference getQuestionReference(Question question) {
        return this.context.getQuestionReference(question);
    }

    public JTabbedPane getLayout(JTabbedPane tabbedPane) {

        for (QuestionReference questionReference : this.context.getQuestions()) {
            Segment parent = this.context.getParent(questionReference.getId());
            if (parent != null && visibleSections.contains(questionReference.getId())) {
                JPanel sectionPanel = sections.get(questionReference.getId());
                JPanel parentPanel = sections.get(parent.getId());
                parentPanel.add(sectionPanel);
            }
        }

        for (Section section : this.context.getSections()) {
            Segment parent = this.context.getParent(section.getId());
            if (parent != null && visibleSections.contains(section.getId())) {
                JPanel sectionPanel = sections.get(section.getId());
                JPanel parentPanel = sections.get(parent.getId());
                parentPanel.add(sectionPanel);
            }
        }

        for (Page page : context.getPages()) {
            if (visibleSections.contains(page.getId())) {
                tabbedPane.add(page.getTitle(), sections.get(page.getId()));
            }
        }
        return tabbedPane;
    }

    public JPanel getPage(Question question) {
        Segment parent = this.context.getPage(question);
        if (parent != null && this.sections.containsKey(parent.getId())) {
            return this.sections.get(parent.getId());
        }
        return null;
    }

    public Style getStyle(QuestionReference questionReference) {
        return new Style(null, null);
    }

    public WidgetType getWidgetType(Question question) {
        if (stylesheet != null) {
            QuestionReference questionReference = this.context.getQuestion(question.getId());
            if (questionReference != null && questionReference.getWidget() != null) {
                return questionReference.getWidget().getType();
            }
        }

        //TODO select scope specific defaults

        return defaultTypes.get(question.getType().getClass().toString());
    }


    private void setDefaultWidgetTypes() {
        defaultTypes.put(StringType.class.toString(), new TextType());
        defaultTypes.put(MoneyType.class.toString(), new TextType());
        defaultTypes.put(IntegerType.class.toString(), new TextType());
        defaultTypes.put(BooleanType.class.toString(), new CheckboxType(""));
    }

    public void generateSections() {
        visibleSections = new ArrayList<>();
        sections = new HashMap<>();
        for (Page page : this.context.getPages()) {
            JPanel pagePanel = new JPanel();
            pagePanel.setLayout(new GridLayout(0, 1));
            sections.put(page.getId(), pagePanel);
        }

        for (Section section : this.context.getSections()) {
            JPanel sectionPanel = new JPanel();
            sectionPanel.setLayout(new GridLayout(0, 1));

            TitledBorder border = BorderFactory.createTitledBorder(section.getTitle());
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
            border.setBorder(lineBorder);
            sectionPanel.setBorder(border);

            sections.put(section.getId(), sectionPanel);
        }
    }

    private void setDefaultSection() {

    }

}
