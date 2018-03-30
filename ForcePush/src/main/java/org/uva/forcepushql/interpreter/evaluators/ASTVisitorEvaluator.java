package org.uva.forcepushql.interpreter.evaluators;


import org.uva.forcepushql.interpreter.gui.EventChecker;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;
import org.uva.forcepushql.interpreter.gui.questions.Question;
import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;
import org.uva.forcepushql.parser.ast.elements.Node;
import org.uva.forcepushql.parser.ast.elements.NumberNode;
import org.uva.forcepushql.parser.ast.elements.QuestionAssignValueNode;
import org.uva.forcepushql.parser.ast.elements.QuestionNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;

import static org.uva.forcepushql.interpreter.gui.JPanelGUI.getString;

//TODO: Refactor the hell out of this. Please. It hurts. Make it stop....
public class ASTVisitorEvaluator implements ASTVisitor
{
    EventChecker eventChecker = new EventChecker();

    @Override
    public LinkedList<JPanel> visit(FormNode node)
    {
        JPanelGUI jPanelGUI = new JPanelGUI();
        LinkedList<JPanel> result = new LinkedList<>();
        LinkedList<Question> questions = new LinkedList<>();

        for (Node n : node.getQuestions())
        {
            if (n instanceof ConditionalIfNode) {
                LinkedList<JPanelGUI> jPanelIf = n.accept(this);

                for (JPanelGUI jpg : jPanelIf) {
                    result.add(jpg.getPanel());
                }
            } else if(n instanceof QuestionAssignValueNode){
                Question question = ((QuestionAssignValueNode) n).accept(this);
                eventChecker.addCalculationPanel(question.answerNameValue(),jPanelGUI);
                questions.add(question);

            }
            else {
                questions.add(n.accept(this));
            }

        }

        jPanelGUI.createPanel(questions, 0);
        JPanel jPanelForm = jPanelGUI.getPanel();


        result.addFirst(jPanelForm);
        return result;
    }

    @Override
    public LinkedList<JPanelGUI> visit(ConditionalIfNode node)
    {
        JPanelGUI jPanelGUI = new JPanelGUI();

        Node condition = node.getCondition();

        if(condition != null) {
            eventChecker.addCondition(condition.accept(this), jPanelGUI);
        }
        else {
            eventChecker.addCondition("",jPanelGUI);
        }

        LinkedList<JPanelGUI> result = new LinkedList<>();
        LinkedList<Question> questions = new LinkedList<Question>();

        for (Node n : node.getQuestions())
        {
            if (n instanceof ConditionalIfNode)
            {
                LinkedList<JPanelGUI> jPanelIf = n.accept(this);
                result.addAll(jPanelIf);

            } else if(n instanceof QuestionAssignValueNode){
                Question question = ((QuestionAssignValueNode) n).accept(this);
                eventChecker.addCalculationPanel(question.answerNameValue(),jPanelGUI);
                questions.add(question);
            }

            else {
                questions.add(n.accept(this));
            }

        }

        if (node.getAfter() != null)
        {

            LinkedList<JPanelGUI> jPanelGUIS = node.getAfter().accept(this);

            result.addAll(jPanelGUIS);

        }

        jPanelGUI.createPanel(questions, 0);
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


    public String visit(NegateNode node)
    {
        return "!" + node.getInnerNode().accept(this);
    }

    @Override
    public Question visit(QuestionNode node)
    {
        Question question;
        String label = visit((LabelNode) node.getLeft());
        String name = visit((NameNode) node.getCenter());
        String type = visit((TypeNode) node.getRight());

        if (type.equals("boolean"))
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
        String calculation = node.getExpression().accept(this);
        Question question = node.getPrevious().accept(this);
        eventChecker.addCalculation(question.answerNameValue(),calculation);
        ((Textbox)question).setHasCalculation(true);

        return question;
    }

    @Override
    public String visit(LabelNode node)
    {
        return node.getLabel().replaceAll("\"", "");
    }

    @Override
    public String visit(NameNode node)
    {
        return node.getName();
    }

    @Override
    public String visit(TypeNode node)
    {
        return node.getType();
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


}
