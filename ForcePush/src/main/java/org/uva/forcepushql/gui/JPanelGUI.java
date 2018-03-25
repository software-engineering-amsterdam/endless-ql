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
    private HashMap<String,Double> numberValues;
    private HashMap<String,String> calculations;
    private String condition;
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

    public void setCondition(String condition) {
        this.condition = condition;
        condition = allTogether(condition);
        String[] result = condition.split("\\.");

        for (String s: result) {
            booleanValues.put(s,false);
        }

    }

    public void addCalculation(String variable, String calculation){
        calculations.put(variable,calculation);
        calculation = allTogether(calculation);
        String[] result = calculation.split("\\.");

        for (String s: result) {
            numberValues.put(s,-1.0);
        }
    }


    private void checkCondition(){

        String toTest = condition;

        for (Map.Entry<String, Boolean> bv: booleanValues.entrySet()) {
            toTest = toTest.replaceAll(bv.getKey(),String.valueOf(bv.getValue()));
        }

        boolean result = createAST(toTest).accept(new ASTExpressionVisitorEvaluator());

        panel.setVisible(result);

    }

    private void checkCalculation(){

        for (Map.Entry<String,String> c: calculations.entrySet()) {
            String toCalculate = c.getValue();

            for (Map.Entry<String,Double> nv: numberValues.entrySet()) {
                toCalculate = toCalculate.replaceAll(nv.getKey(),String.valueOf(nv.getValue()));
            }

            if (!toCalculate.contains("-1.0")){
                double result = createAST(toCalculate).accept(new ASTExpressionVisitorEvaluator());
                ((TextboxGUI) getQuestion(c.getKey())).setText(String.valueOf(result));
            }
        }

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


    @Override
    public void updateRadio(Radio radio)
    {
        booleanValues.put(radio.answerNameValue(), radio.answerValue());
        checkCondition();
    }

    @Override
    public void updateTextbox(Textbox textbox)
    {
        numberValues.put(textbox.answerNameValue(), Double.valueOf(textbox.answerValue()));
        checkCalculation();
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

    private String allTogether(String string){
        string = string.replaceAll("&&",".");
        string = string.replaceAll("\\|\\|",".");
        string = string.replaceAll("<",".");
        string = string.replaceAll(">",".");
        string = string.replaceAll("<=",".");
        string = string.replaceAll(">=",".");
        string = string.replaceAll("!=",".");
        string = string.replaceAll("==",".");
        string = string.replaceAll("!","");
        string = string.replaceAll("\\+",".");
        string = string.replaceAll("-",".");
        string = string.replaceAll("\\*",".");
        string = string.replaceAll("/",".");
        string = string.replaceAll(" ","");

        return string;
    }

    private ExpressionNode createAST(String input){
        ANTLRInputStream expression = new ANTLRInputStream(input);
        GrammarLexer lexer = new GrammarLexer(expression);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ExpressionNode mathUnit = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());

        return mathUnit;
    }
}
