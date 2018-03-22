package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Radio;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RadioGUI implements QuestionGUI{

    private Radio question;
    private JLabel label;
    private JRadioButton[] options;

    public RadioGUI(Radio radio){
        question = radio;
        label = new JLabel(question.writtenQuestion(), JLabel.CENTER);
        JRadioButton option1 = new JRadioButton("Yes");
        JRadioButton option2 = new JRadioButton("No");

        ButtonGroup bg = new ButtonGroup();
        bg.add(option1);
        bg.add(option2);

        options = new JRadioButton[2];
        options[0] = option1;
        options[1] = option2;
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public String getVariable() {
        return question.answerNameValue();
    }

    public JRadioButton[] getOptions() {
        return options;
    }

    public void addActionListener(ActionListener actionListener){
        options[0].addActionListener(actionListener);
        options[1].addActionListener(actionListener);
    }
}
