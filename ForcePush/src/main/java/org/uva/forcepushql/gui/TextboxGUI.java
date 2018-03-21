package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TextboxGUI implements QuestionGUI{

    private Textbox question;
    private JLabel label;
    private JTextField textField;
    private LinkedList<Observer> observers;

    public TextboxGUI(Textbox textbox){
        observers = new LinkedList<Observer>();
        question =textbox;
        label = new JLabel(question.writtenQuestion(), JLabel.CENTER);
        textField = new JTextField(5);

        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JTextField) {
                    JTextField textField = (JTextField) e.getSource();
                    String textFieldValue = textField.getText();
                    question.givenAnswer(textFieldValue);
                }
            }
        };
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

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer obs: observers) {
            obs.updateTextbox(question);
        }
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
