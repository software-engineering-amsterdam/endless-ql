package org.uva.forcepushql.interpreter.gui;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;
import org.uva.forcepushql.parser.ast.visitors.BuildASTExpressionVisitor;
import org.uva.forcepushql.interpreter.gui.questions.Question;
import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JPanelGUI{

    private LinkedList<QuestionGUI> questionGUIS;
    private HashMap<String, Boolean> booleanValues;
    private HashMap<String, Double> numberValues;
    private HashMap<String, String> calculations;
    private int height = 0;
    private JPanel panel;

    public JPanelGUI()
    {
        booleanValues = new HashMap<>();
        numberValues = new HashMap<>();
        calculations = new HashMap<>();
    }

    public void createPanel(LinkedList<Question> questions, int height)
    {
        panel = new JPanel();
        questionGUIS = new LinkedList<QuestionGUI>();

        for (Question q : questions)
        {

            this.height += 30;


            if (q instanceof Radio)
            {
                RadioGUI radioGUI = new RadioGUI((Radio) q);
                panel.add(radioGUI.getLabel());
                panel.add(radioGUI.getOptions()[0]);
                panel.add(radioGUI.getOptions()[1]);
                questionGUIS.add(radioGUI);
            } else if (q instanceof Textbox)
            {
                TextboxGUI textboxGUI = new TextboxGUI((Textbox) q);
                panel.add(textboxGUI.getLabel());
                panel.add(textboxGUI.getTextField());
                questionGUIS.add(textboxGUI);
            }
        }

        panel.setPreferredSize(new Dimension(300, height + this.height));

    }

    public int getHeight()
    {
        return height;
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
