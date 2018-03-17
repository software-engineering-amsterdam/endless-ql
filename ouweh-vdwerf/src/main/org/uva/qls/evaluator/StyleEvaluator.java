package org.uva.qls.evaluator;

import org.uva.qls.ast.Segment.Page;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.ast.Widget.WidgetTypes.CheckboxType;
import org.uva.qls.ast.Widget.WidgetTypes.TextType;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.*;
import org.uva.qls.collector.StylesheetContext;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

public class StyleEvaluator {

    private Stylesheet stylesheet;
    private StylesheetContext context;

    private Map<String, WidgetType> defaultTypes = new HashMap<>();

    private Map<String, JPanel> pages = new HashMap<>();
    private Map<String, JPanel> sections = new HashMap<>();

    public StyleEvaluator(){
        setDefaultWidgetTypes();
        setDefaultSection();
    }

    public void setStylesheet(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
        this.context = new StylesheetContext(stylesheet);
    }

    public JTabbedPane getPages() {
        JTabbedPane tabbedPane = new JTabbedPane();
        for (Page page : context.getPages()){
            JPanel panel = new JPanel();
            pages.put(page.getId(), panel);
            tabbedPane.add(page.getId(), panel);
        }
        return tabbedPane;
    }

    public Style getStyle(QuestionReference questionReference){
        return new Style(null, null);
    }

    public WidgetType getWidgetType(Question question){
        if(stylesheet != null) {
            QuestionReference questionReference = this.context.getQuestion(question.getId());
            if(questionReference != null && questionReference.getWidget() != null) {
                return questionReference.getWidget().getType();
            }
        }

        //TODO select scope specific defaults

        return defaultTypes.get(question.getType().getClass().toString());
    }


    private void setDefaultWidgetTypes(){
        defaultTypes.put(StringType.class.toString(), new TextType());
        defaultTypes.put(MoneyType.class.toString(), new TextType());
        defaultTypes.put(IntegerType.class.toString(), new TextType());
        defaultTypes.put(BooleanType.class.toString(), new CheckboxType(""));
    }

    private void setDefaultSection(){

    }

}
