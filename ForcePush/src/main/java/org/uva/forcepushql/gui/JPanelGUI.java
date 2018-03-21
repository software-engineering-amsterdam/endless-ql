package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Question;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class JPanelGUI extends Observer{

    private LinkedList<QuestionGUI> questionGUIS;
    private int height = 0;
    private JPanel panel;

    public JPanelGUI(){

    }

    public void createPanel(LinkedList<Question> questions, int height){
        panel = new JPanel();
        questionGUIS = new LinkedList<QuestionGUI>();

        for (Question q: questions) {

            this.height += 30;


            if (q instanceof Radio){
                RadioGUI radioGUI = new RadioGUI((Radio) q);
                panel.add(radioGUI.getLabel());
                panel.add(radioGUI.getOptions()[0]);
                panel.add(radioGUI.getOptions()[1]);
                questionGUIS.add(radioGUI);
            }

            else  if (q instanceof Textbox){
                TextboxGUI textboxGUI = new TextboxGUI((Textbox) q);
                panel.add(textboxGUI.getLabel());
                panel.add(textboxGUI.getTextField());
                questionGUIS.add(textboxGUI);
            }
        }

        panel.setPreferredSize(new Dimension(300, height + this.height));

    }

    public int getHeight(){
        return height;
    }

    public void addActionListener(String answerName, ActionListener actionListener){

        for (QuestionGUI q: questionGUIS) {

            if (q.getVariable().equals(answerName)){
                q.addActionListener(actionListener);
            }

        }
    }

    public void setEnable(boolean bool, String answerName){

        for (QuestionGUI q: questionGUIS) {

            if (q instanceof TextboxGUI && q.getVariable().equals(answerName)){
                ((TextboxGUI) q).setEnable(bool);
            }
        }
    }

    public void setText(String text, String answerName){

        for (QuestionGUI q: questionGUIS) {

            if(q instanceof TextboxGUI && q.getVariable().equals(answerName)){
                ((TextboxGUI) q).setText(text);
            }
        }
    }

    @Override
    public void updateRadio(Radio radio) {
        boolean state = radio.answerValue();

        if (state)
            panel.setVisible(true);

        else
            panel.setVisible(false);
    }

    @Override
    public void updateTextbox(Textbox textbox) {

    }

    public QuestionGUI getQuestion(String name){
        QuestionGUI questionGUI = null;
        for (QuestionGUI q: questionGUIS) {
            if (q.getVariable().equals(name))
                questionGUI = q;
        }

        return questionGUI;
    }

    public JPanel getPanel(){
        return panel;
    }
}
