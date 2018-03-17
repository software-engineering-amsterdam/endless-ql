package qls.analysis;

import ql.model.Form;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.Question;
import qls.model.StyleSheet;
import qls.model.widgets.WidgetType;

import java.util.HashMap;
import java.util.Map;

public class TypeChecker {

    public void typeCheck(Form form, StyleSheet styleSheet) {
        // Get question types
        Map<String, ReturnType> formQuestionTypes = getFormQuestionTypes(form);

        // Compare to QLS question widget types
        styleSheet.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                if(question.getWidget() == null) {
                    return super.visit(question);
                }

                WidgetType widgetType = question.getWidget().type;
                ReturnType questionType = formQuestionTypes.get(question.name);
                if(!widgetType.isCompatible(questionType)) {
                    throw new IllegalArgumentException("Incompatible widget type " + widgetType
                            + " for question of type " + questionType + " " + question.getWidget().getLocation());
                }

                return super.visit(question);
            }
        });
    }

    private Map<String, ReturnType> getFormQuestionTypes(Form form) {
        Map<String, ReturnType> questionTypes = new HashMap<>();
        for(ql.model.Question question : form.questions) {
            questionTypes.put(question.name, question.type);
        }

        return questionTypes;
    }
}
