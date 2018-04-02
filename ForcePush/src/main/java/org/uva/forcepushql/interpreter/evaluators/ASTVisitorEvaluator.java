package org.uva.forcepushql.interpreter.evaluators;


import org.uva.forcepushql.interpreter.TypeChecker.Messages;
import org.uva.forcepushql.interpreter.gui.EventChecker;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;
import org.uva.forcepushql.interpreter.gui.questions.Question;
import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;
import org.uva.forcepushql.parser.ast.ValueType;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class ASTVisitorEvaluator implements ASTVisitor
{
    EventChecker eventChecker = new EventChecker();
    String elseCondition = "";

    Messages messages = new Messages();
    HashMap<String, ValueType> declaredVaribles = new HashMap<>();
    LinkedList<String> labels = new LinkedList<>();

    private static String[] variables(String expression)
    {
        return EventChecker.getString(expression).split("\\.");
    }

    @Override
    public LinkedList<JPanel> visit(FormNode node)
    {
        JPanelGUI jPanelGUI = new JPanelGUI();
        LinkedList<JPanel> result = new LinkedList<>();
        LinkedList<JPanelGUI> jPanelGUIS = new LinkedList<>();
        LinkedList<Question> questions = new LinkedList<>();

        makeQuestionsList(node.getQuestions(), jPanelGUIS, jPanelGUI, questions);

        for (JPanelGUI jpg : jPanelGUIS)
        {
            result.add(jpg.getPanel());
        }

        jPanelGUI.createPanel(questions);
        JPanel jPanelForm = jPanelGUI.getPanel();


        result.addFirst(jPanelForm);

        if (!messages.isEmpty() && !messages.allWarning())
        {
            System.err.print(messages.toString());
            System.exit(1);
        } else if (messages.allWarning())
        {
            System.err.print(messages.toString());
        }

        return result;
    }

    @Override
    public LinkedList<JPanelGUI> visit(ConditionalNode node)
    {
        JPanelGUI jPanelGUI = new JPanelGUI();

        Node condition = node.getCondition();

        if (condition != null)
        {

            String expression = condition.accept(this);

            if (!condition.isBooleanExpression())
            {
                messages.addMessage("The expression " + expression + " on conditional is not a boolean expression",
                        Messages.MessageTypes.ERROR);
            }

            containsAllVariables(expression);

            eventChecker.addCondition(expression, jPanelGUI);
            if (elseCondition.equals(""))
            {
                elseCondition = "!" + expression;
            } else
            {
                elseCondition = "!" + expression + " && " + elseCondition;
            }
        } else
        {
            eventChecker.addCondition(elseCondition, jPanelGUI);
            elseCondition = "";
        }

        LinkedList<JPanelGUI> result = new LinkedList<>();
        LinkedList<Question> questions = new LinkedList<Question>();

        makeQuestionsList(node.getQuestions(), result, jPanelGUI, questions);


        if (node.getAfter() != null)
        {

            LinkedList<JPanelGUI> jPanelGUIS = node.getAfter().accept(this);

            result.addAll(jPanelGUIS);

        }

        jPanelGUI.createPanel(questions);
        JPanel ifElsePanel = jPanelGUI.getPanel();
        ifElsePanel.setVisible(false);
        result.addFirst(jPanelGUI);


        return result;
    }

    @Override
    public String visit(AdditionNode node)
    {
        return node.getLeft().accept(this) + " + " + node.getRight().accept(this);
    }

    @Override
    public String visit(SubtractionNode node)
    {
        return node.getLeft().accept(this) + " - " + node.getRight().accept(this);
    }

    @Override
    public String visit(MultiplicationNode node)
    {
        return node.getLeft().accept(this) + " * " + node.getRight().accept(this);
    }

    @Override
    public String visit(DivisionNode node)
    {
        return node.getLeft().accept(this) + " / " + node.getRight().accept(this);
    }

    @Override
    public String visit(AndNode node)
    {
        return node.getLeft().accept(this) + " && " + node.getRight().accept(this);
    }

    @Override
    public String visit(OrNode node)
    {
        return node.getLeft().accept(this) + " || " + node.getRight().accept(this);
    }

    @Override
    public String visit(LessNode node)
    {
        return node.getLeft().accept(this) + " < " + node.getRight().accept(this);
    }

    @Override
    public String visit(GreaterNode node)
    {
        return node.getLeft().accept(this) + " > " + node.getRight().accept(this);
    }

    @Override
    public String visit(EqualLessNode node)
    {
        return node.getLeft().accept(this) + " <= " + node.getRight().accept(this);
    }

    @Override
    public String visit(EqualGreaterNode node)
    {
        return node.getLeft().accept(this) + " >= " + node.getRight().accept(this);
    }

    @Override
    public String visit(NotEqualNode node)
    {
        return node.getLeft().accept(this) + " != " + node.getRight().accept(this);
    }

    @Override
    public String visit(IsEqualNode node)
    {
        return node.getLeft().accept(this) + " == " + node.getRight().accept(this);
    }

    public String visit(NotNode node)
    {
        return "!" + node.getInnerNode().accept(this);
    }

    @Override
    public Question visit(QuestionNode node)
    {
        Question question;
        String label = visit((LabelNode) node.getLeft());
        String name = visit((NameNode) node.getCenter());
        ValueType type = visit((TypeNode) node.getRight());

        if (!declaredVaribles.containsKey(name))
        {
            declaredVaribles.put(name, type);
        } else if (!declaredVaribles.get(name).equals(type))
        {
            messages.addMessage("The variable " + name + " is already declared", Messages.MessageTypes.ERROR);
        }

        if (type.equals(ValueType.BOOLEAN))
        {
            question = new Radio(label, type, name);
        } else
        {
            question = new Textbox(label, type, name);
        }

        question.attachObserver(eventChecker);
        return question;

    }

    @Override
    public Question visit(QuestionAssignValueNode node)
    {
        Question question = node.getPrevious().accept(this);
        Node expression = node.getExpression();

        if (expression.isBooleanExpression())
        {
            messages.addMessage("The calculation on variable " + question.answerNameValue() + " is not a math expression.",
                    Messages.MessageTypes.ERROR);
        }

        String calculation = expression.accept(this);

        containsAllVariables(calculation);

        eventChecker.addCalculation(question.answerNameValue(), calculation);
        ((Textbox) question).setHasCalculation(true);

        return question;
    }

    @Override
    public String visit(LabelNode node)
    {
        String label = node.getLabel().replaceAll("\"", "");

        if (labels.contains(label))
        {
            messages.addMessage("Label " + label + " already exists", Messages.MessageTypes.WARNING);
        } else
        {
            labels.add(label);
        }

        return label;
    }

    @Override
    public String visit(NameNode node)
    {
        return node.getName();
    }

    @Override
    public ValueType visit(TypeNode node)
    {
        ValueType valueType = node.getType();
        if (valueType.equals(ValueType.UNKNOWN))
        {
            messages.addMessage("Error: Incorrect type! Types allowed are: money, string, boolean, decimal and integer.",
                    Messages.MessageTypes.ERROR);
        }
        return valueType;
    }

    @Override
    public String visit(VariableNode node)
    {
        return node.getName();
    }

    @Override
    public String visit(DecimalNode node)
    {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visit(NumberNode node)
    {
        return String.valueOf(node.getValue());
    }

    private void makeQuestionsList(List<Node> listToTurn, List<JPanelGUI> result,
                                   JPanelGUI jPanelGUI, List<Question> questions)
    {
        for (Node n : listToTurn)
        {
            if (n instanceof ConditionalNode)
            {
                LinkedList<JPanelGUI> jPanelIf = n.accept(this);
                result.addAll(jPanelIf);

            } else if (n instanceof QuestionAssignValueNode)
            {
                Question question = ((QuestionAssignValueNode) n).accept(this);
                eventChecker.addCalculationPanel(question.answerNameValue(), jPanelGUI);
                questions.add(question);
            } else
            {
                questions.add(n.accept(this));
            }

        }
    }

    private void containsAllVariables(String expression)
    {
        boolean containsAll = true;
        for (String var : variables(expression))
        {
            containsAll = containsAll && declaredVaribles.containsKey(var);
        }

        if (!containsAll)
            messages.addMessage("Not all variables from expression " + expression + " are declared",
                    Messages.MessageTypes.ERROR);
    }

}
