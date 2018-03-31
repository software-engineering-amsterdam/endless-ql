package gui.questions.text;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.GUIBuilder;
import gui.listeners.QuestionValueListener;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.util.EventListener;

public class QuestionWidgetTextInt extends QuestionWidgetText {
    public QuestionWidgetTextInt(String key, Question question) {
        super(key, question);
    }

    @Override
    public void setQuestionChangeListener(QuestionValueListener questionValueListener) {
        JTextField textField = (JTextField) super.getComponent();
        textField.getDocument().addDocumentListener(questionValueListener.new IntegerDocumentListener(super.getQuestion().getId(), textField));
    }

}
