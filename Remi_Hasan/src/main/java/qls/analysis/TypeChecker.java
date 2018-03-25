package qls.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.DefaultStyle;
import qls.model.QuestionReference;
import qls.model.StyleSheet;
import qls.model.widget.WidgetType;

import java.util.HashMap;
import java.util.Map;

public class TypeChecker extends QLSVisitor<Void> implements IQLSAnalysis {

    private Map<String, ReturnType> formQuestionTypes;

    @Override
    public void analyze(Form form, StyleSheet styleSheet) {
        // Get question types
        this.formQuestionTypes = getFormQuestionTypes(form);

        // Compare to QLS question widget types
        styleSheet.accept(this);
    }

    @Override
    public Void visit(QuestionReference questionReference) {
        if(questionReference.getWidget() == null) {
            return super.visit(questionReference);
        }

        // Check if QLS widget is compatible with question type
        WidgetType widgetType = questionReference.getWidget().getType();
        ReturnType questionType = this.formQuestionTypes.get(questionReference.getIdentifier());
        if(!widgetType.isCompatible(questionType)) {
            throw new IllegalArgumentException("Incompatible widget type " + widgetType
                    + " for question of type " + questionType + " " + questionReference.getWidget().getLocation());
        }

        return super.visit(questionReference);
    }

    @Override
    public Void visit(DefaultStyle defaultStyle) {
        if(defaultStyle.getWidget() == null) {
            return super.visit(defaultStyle);
        }

        // Check if widget type defined in the default style is compatible with the
        // question type that is being styled
        WidgetType widgetType = defaultStyle.getWidget().getType();
        if(!widgetType.isCompatible(defaultStyle.getType())) {
            throw new IllegalArgumentException("Incompatible widget type " + widgetType
                    + " for question of type " + defaultStyle.getType() + " " + defaultStyle.getWidget().getLocation());
        }

        return super.visit(defaultStyle);
    }

    private Map<String, ReturnType> getFormQuestionTypes(Form form) {
        Map<String, ReturnType> questionTypes = new HashMap<>();

        // Visit questions and add their return type to the map
        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(ql.model.Question question) {
                questionTypes.put(question.getIdentifier(), question.getType());
                return super.visit(question);
            }
        });

        return questionTypes;
    }
}
