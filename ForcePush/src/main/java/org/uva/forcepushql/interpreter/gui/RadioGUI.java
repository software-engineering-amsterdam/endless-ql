package org.uva.forcepushql.interpreter.gui;

import org.uva.forcepushql.interpreter.gui.questions.Radio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RadioGUI implements QuestionGUI
{

    private Radio question;
    private JLabel label;
    private JRadioButton[] options;
    private LinkedList<Observer> observers;

    public RadioGUI(Radio radio)
    {
        observers = new LinkedList<Observer>();
        question = radio;
        label = new JLabel(question.writtenQuestion(), JLabel.CENTER);
        JRadioButton option1 = new JRadioButton("Yes");
        JRadioButton option2 = new JRadioButton("No");

        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() instanceof JRadioButton)
                {
                    JRadioButton radioButton = (JRadioButton) e.getSource();

                    if (radioButton.isSelected() && (radioButton.getText() == "Yes"))
                    {
                        radio.givenAnswer(true);
                        notifyAllObservers();

                    } else if (radioButton.isSelected() && (radioButton.getText() == "No"))
                    {
                        radio.givenAnswer(false);
                        notifyAllObservers();

                    }

                }
            }
        };

        ButtonGroup bg = new ButtonGroup();
        bg.add(option1);
        bg.add(option2);

        option1.addActionListener(actionListener);
        option2.addActionListener(actionListener);

        options = new JRadioButton[2];
        options[0] = option1;
        options[1] = option2;
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

    public JRadioButton[] getOptions()
    {
        return options;
    }

    public void addActionListener(ActionListener actionListener)
    {
        options[0].addActionListener(actionListener);
        options[1].addActionListener(actionListener);
    }

    public void attachObserver(Observer observer)
    {
        observers.add(observer);
    }

    public void notifyAllObservers()
    {
        for (Observer obs : observers)
        {
            obs.updateRadio(question);
        }
    }
}
