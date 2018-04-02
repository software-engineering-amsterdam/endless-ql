package org.uva.forcepushql.interpreter.gui;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.forcepushql.interpreter.evaluators.ASTExpressionVisitorEvaluator;
import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;
import org.uva.forcepushql.parser.ast.visitors.BuildASTExpressionVisitor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventChecker extends Observer
{

    private LinkedHashMap<String, JPanelGUI> conditionals;
    private HashMap<String, String> calculations;
    private HashMap<String, Boolean> booleanValues;
    private HashMap<String, Double> numberValues;
    private HashMap<String, JPanelGUI> calculationPanel;

    public EventChecker()
    {
        conditionals = new LinkedHashMap<>();
        calculations = new HashMap<>();
        booleanValues = new HashMap<>();
        numberValues = new HashMap<>();
        calculationPanel = new HashMap<>();
    }

    public static String getString(String string)
    {
        string = string.replaceAll("&&", ".");
        string = string.replaceAll("\\|\\|", ".");
        string = string.replaceAll("<", ".");
        string = string.replaceAll(">", ".");
        string = string.replaceAll("<=", ".");
        string = string.replaceAll(">=", ".");
        string = string.replaceAll("!=", ".");
        string = string.replaceAll("==", ".");
        string = string.replaceAll("!", "");
        string = string.replaceAll("\\+", ".");
        string = string.replaceAll("-", ".");
        string = string.replaceAll("\\*", ".");
        string = string.replaceAll("/", ".");
        string = string.replaceAll(" ", "");

        return string;
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

    public void addCalculationPanel(String question, JPanelGUI jPanelGUI)
    {
        calculationPanel.put(question, jPanelGUI);
    }

    public void addCondition(String condition, JPanelGUI panel)
    {
        conditionals.put(condition, panel);
        condition = allTogether(condition);
        String[] result = condition.split("\\.");

        for (String s : result)
        {
            booleanValues.put(s, null);
        }

    }

    public void addCalculation(String variable, String calculation)
    {
        calculations.put(variable, calculation);
        calculation = allTogether(calculation);
        String[] result = calculation.split("\\.");

        for (String s : result)
        {
            if (!isNumeric(s))
            {
                numberValues.put(s, -1.0);
            }
        }


    }

    private void checkCondition()
    {
        for (Map.Entry<String, JPanelGUI> condition : conditionals.entrySet())
        {
            String toTest = condition.getKey();

            for (Map.Entry<String, Boolean> bv : booleanValues.entrySet())
            {
                if (bv.getValue() != null && toTest.contains(bv.getKey()))
                {
                    toTest = toTest.replaceAll(bv.getKey(), String.valueOf(bv.getValue()));
                    boolean result = createAST(toTest).accept(new ASTExpressionVisitorEvaluator());
                    condition.getValue().getPanel().setVisible(result);
                }
            }
        }
    }

    private void checkCalculation()
    {

        for (Map.Entry<String, String> calculation : calculations.entrySet())
        {
            String toCalculate = calculation.getValue();

            for (Map.Entry<String, Double> nv : numberValues.entrySet())
            {
                toCalculate = toCalculate.replaceAll(nv.getKey(), String.valueOf(nv.getValue()));
            }

            if (!toCalculate.contains("-1.0"))
            {
                double result = createAST(toCalculate).accept(new ASTExpressionVisitorEvaluator());
                JPanelGUI panelGUI = calculationPanel.get(calculation.getKey());
                ((TextboxGUI) panelGUI.getQuestion(calculation.getKey())).setText(String.valueOf(result));
            }
        }

    }

    private String allTogether(String string)
    {
        return getString(string);
    }

    private ExpressionNode createAST(String input)
    {
        ANTLRInputStream expression = new ANTLRInputStream(input);
        GrammarLexer lexer = new GrammarLexer(expression);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ExpressionNode mathUnit = new BuildASTExpressionVisitor().visitMathUnit(parser.mathUnit());

        return mathUnit;
    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
