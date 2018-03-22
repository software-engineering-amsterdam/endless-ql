package org.uva.qls.evaluator;

import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.StringType;
import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.*;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.*;
import org.uva.qls.ast.Value.ColorValue;
import org.uva.qls.ast.Value.NumberValue;
import org.uva.qls.ast.Value.StringValue;
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
import java.util.Arrays;

public class StyleEvaluator {

    private Stylesheet stylesheet;
    private StylesheetContext context;

    private Map<String, WidgetType> defaultTypes = new HashMap<>();

    private Map<String, JPanel> sections = new HashMap<>();
    private List<String> visibleSections = new ArrayList<>();

    private Style defaultStyle;

    public StyleEvaluator() {
        setDefaultWidgetTypes();
        setDefaultStyle();

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

    public Style getStyle(Question question) {
        QuestionReference questionReference = this.context.getQuestionReference(question);
        for (Segment segment: this.context.getAllParents(questionReference.getId())){
            for(DefaultStyleStatement defaultStyleStatement: segment.getDefaultStyleStatements()){
                if (defaultStyleStatement.getType().getClass().equals(question.getType().getClass())){
                    return defaultStyleStatement.getStyle();
                }
            }
        }
        return defaultStyle;
    }

    public WidgetType getWidgetType(Question question) {
        QuestionReference questionReference = this.context.getQuestionReference(question);
        if (questionReference.getWidget() != null) {
            return questionReference.getWidget().getType();
        }

        for(Segment segment: this.context.getAllParents(questionReference.getId())) {
            for (DefaultWidgetStatement defaultWidgetStatement : segment.getDefaultWidgetStatements()) {
                if (defaultWidgetStatement.getType().getClass().equals(question.getType().getClass())){
                    return defaultWidgetStatement.getWidget().getType();
                }
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


    private void setDefaultStyle() {
        List<StyleProperty> properties = new ArrayList<>();
        properties.add(new ColorProperty(new ColorValue("#effeee")));
        properties.add(new FontProperty(new StringValue("Times New Roman")));
        properties.add(new FontSizeProperty(new NumberValue("25")));
        properties.add(new WidthProperty(new NumberValue("100")));
        this.defaultStyle = new Style(properties, null);
    }

}
