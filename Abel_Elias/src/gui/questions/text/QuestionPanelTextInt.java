package gui.questions.text;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.FormBuilder;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.util.EventListener;

public class QuestionPanelTextInt extends QuestionPanelText{
    public QuestionPanelTextInt(String key, Question question) {
        super(key, question);
    }

    @Override
    public void setListener(EventListener listener) {
        FormBuilder.IntegerDocumentListener intListener = (FormBuilder.IntegerDocumentListener) listener;
        JTextField textField = (JTextField) getComponent();
        textField.getDocument().addDocumentListener(intListener);
    }

}
