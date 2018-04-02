package org.uva.forcepushql.interpreter.gui;

import org.uva.forcepushql.interpreter.gui.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TextboxGUI implements QuestionGUI
{

    private Textbox question;
    private JLabel label;
    private JTextField textField;
    private LinkedList<Observer> observers;

    public TextboxGUI(Textbox textbox)
    {
        observers = new LinkedList<Observer>();
        question = textbox;
        label = new JLabel(question.writtenQuestion(), JLabel.CENTER);
        textField = new JTextField(5);

        if(!textbox.hasCalculation()) {
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof JTextField) {
                        JTextField textField = (JTextField) e.getSource();
                        String textFieldValue = textField.getText();
                        question.givenAnswer(textFieldValue);
                    }
                }
            };

            textField.addActionListener(listener);
        }

        else {
            textField.setEnabled(false);
            textField.setDisabledTextColor(Color.BLACK);
        }
    }

    public JLabel getLabel()
    {
        return label;
    }

    @Override
    public String getVariable()
    {
        return question.answerNameValue();
    }

    public JTextField getTextField()
    {
        return textField;
    }


    public void setText(String text)
    {
        textField.setText(text);
    }
}
