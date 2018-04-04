package org.uva.forcepushql.interpreter.gui;

import org.uva.forcepushql.interpreter.gui.questions.Question;
import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JPanelGUI{

    private LinkedList<QuestionGUI> questionGUIS;
    private JPanel panel;


    public void createPanel(List<Question> questions)
    {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        questionGUIS = new LinkedList<QuestionGUI>();

        for (Question q : questions)
        {
            if (q instanceof Radio)
            {
                JPanel smallPanel = new JPanel();
                RadioGUI radioGUI = new RadioGUI((Radio) q);
                smallPanel.add(radioGUI.getLabel());
                smallPanel.add(radioGUI.getOptions()[0]);
                smallPanel.add(radioGUI.getOptions()[1]);
                questionGUIS.add(radioGUI);
                panel.add(smallPanel);

            } else if (q instanceof Textbox)
            {
                JPanel smallPanel = new JPanel();
                TextboxGUI textboxGUI = new TextboxGUI((Textbox) q);
                smallPanel.add(textboxGUI.getLabel());
                smallPanel.add(textboxGUI.getTextField());
                questionGUIS.add(textboxGUI);
                panel.add(smallPanel);
            }
        }

        panel.revalidate();
    }


    public QuestionGUI getQuestion(String name)
    {
        QuestionGUI questionGUI = null;
        for (QuestionGUI q : questionGUIS)
        {
            if (q.getVariable().equals(name))
                questionGUI = q;
        }

        return questionGUI;
    }

    public JPanel getPanel()
    {
        return panel;
    }


}
