package gui.questions.text;

import QL.classes.Question;
import gui.GUIBuilder;
import gui.QuestionChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.util.EventListener;

public class QuestionPanelTextString extends QuestionPanelText{
    public QuestionPanelTextString(String key, Question question) {
        super(key, question);
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        JTextField textField = (JTextField) super.getComponent();
        textField.getDocument().addDocumentListener(questionChangeListener.new StringDocumentListener(super.getQuestion().getId(), textField));
    }
}
