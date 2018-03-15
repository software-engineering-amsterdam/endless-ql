package org.uva.qls.evaluator;

import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Stylesheet;
import org.uva.qls.ast.Widget.WidgetTypes.TextType;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StyleEvaluator {

    private Stylesheet stylesheet;
    private Map<String, QuestionReference> questions = new HashMap<>();

    public StyleEvaluator(){

    }

    public void setStylesheet(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;

        for (QuestionReference questionReference : stylesheet.getQuestions()) {
            questions.put(questionReference.getId(), questionReference);
        }
    }

    public Style getStyle(QuestionReference questionReference){
        return new Style(null, null);
    }

    public WidgetType getWidgetType(String questionId){
        if(stylesheet != null) {
            QuestionReference questionReference = this.questions.get(questionId);
            if(questionReference != null) {
                return questionReference.getWidget().getType();
            }
        }
        return new TextType();
    }

}
