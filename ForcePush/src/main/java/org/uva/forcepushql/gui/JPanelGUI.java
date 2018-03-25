package org.uva.forcepushql.gui;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.forcepushql.antlr.GrammarLexer;
import org.uva.forcepushql.antlr.GrammarParser;
import org.uva.forcepushql.ast.*;
import org.uva.forcepushql.questions.Question;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JPanelGUI extends Observer
{

    private LinkedList<QuestionGUI> questionGUIS;
    private HashMap<String,Boolean> booleanValues;
    private String condition;
    private int height = 0;
    private JPanel panel;

    public JPanelGUI()
    {
        booleanValues = new HashMap<>();
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

    public void setCondition(String condition) {
        this.condition = condition;
        condition = condition.replaceAll("&&",".");
        condition = condition.replaceAll("\\|\\|",".");
        condition = condition.replaceAll("<",".");
        condition = condition.replaceAll(">",".");
        condition = condition.replaceAll("<=",".");
        condition = condition.replaceAll(">=",".");
        condition = condition.replaceAll("!=",".");
        condition = condition.replaceAll("==",".");
        condition = condition.replaceAll("!","");
        condition = condition.replaceAll(" ","");

        String[] result = condition.split("\\.");

        for (String s: result) {
            booleanValues.put(s,false);
        }

    }

    public String getCondition() {
        return condition;
    }

    public void checkCondition(){

        String toTest = condition;

        for (Map.Entry<String, Boolean> bv: booleanValues.entrySet()) {
            toTest = toTest.replaceAll(bv.getKey(),String.valueOf(bv.getValue()));
        }
        ANTLRInputStream expression = new ANTLRInputStream(toTest);
        GrammarLexer lexer = new GrammarLexer(expression);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ExpressionNode mathUnit = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());
        boolean result = mathUnit.accept(new ASTExpressionVisitorEvaluator());

        panel.setVisible(result);

    }

    public int getHeight()
    {
        return height;
    }

    public void setEnable(boolean bool, String answerName)
    {

        for (QuestionGUI q : questionGUIS)
        {

            if (q instanceof TextboxGUI && q.getVariable().equals(answerName))
            {
                ((TextboxGUI) q).setEnable(bool);
            }
        }
    }

    public void setText(String text, String answerName)
    {

        for (QuestionGUI q : questionGUIS)
        {

            if (q instanceof TextboxGUI && q.getVariable().equals(answerName))
            {
                ((TextboxGUI) q).setText(text);
            }
        }
    }

    @Override
    public void updateRadio(Radio radio)
    {
        //panel.setVisible(radio.answerValue());

        booleanValues.put(radio.answerNameValue(), radio.answerValue());
        checkCondition();
    }

    @Override
    public void updateTextbox(Textbox textbox)
    {

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
