package gui.factory;

import QL.classes.Question;
import QL.classes.values.Value;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.Widget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.visitors.WidgetVisitor;
import gui.QLSBuilder;
import gui.listeners.QuestionValueListener;
import gui.questions.QuestionPanel;
import gui.questions.QuestionWidgetCheckBox;
import gui.questions.QuestionWidgetDate;
import gui.questions.text.QuestionWidgetTextInt;
import gui.questions.text.QuestionWidgetTextString;

public class WidgetFactory {
    private final QuestionValueListener questionValueListener;
    private final QLSBuilder qlsBuilder;

    public WidgetFactory(QuestionValueListener questionValueListener, QLSBuilder qlsBuilder) {
        this.questionValueListener = questionValueListener;
        this.qlsBuilder = qlsBuilder;
    }

    public QuestionPanel buildQuestionPanel(Question question) {
        WidgetType type = qlsBuilder.getWidgetType(question);
        QuestionPanel panel = createWidget(question, (CheckBoxWidget) type);
        panel.setQuestionChangeListener(questionValueListener);
        return panel;
    }

    public QuestionPanel createWidget(Question question, CheckBoxWidget widget) {
        return new QuestionWidgetCheckBox(question.getText(), question);
    }

}