package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextboxGUI implements QuestionGUI{

    private Textbox question;
    private JLabel label;
    private JTextField textField;

    public TextboxGUI(Textbox textbox){
        question =textbox;
        label = new JLabel(question.writtenQuestion(), JLabel.CENTER);
        textField = new JTextField(5);
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public String getVariable() {
        return question.answerNameValue();
    }

    public JTextField getTextField() {
        return textField;
    }

    public void addActionListener(ActionListener actionListener){
        textField.addActionListener(actionListener);
    }

    public void setEnable(boolean b){
        if (!b){
            textField.setEnabled(b);
            textField.setDisabledTextColor(Color.BLACK);
        }

        else {
            textField.setEnabled(b);
        }
    }

    public void setText(String text){
        textField.setText(text);
    }
}
