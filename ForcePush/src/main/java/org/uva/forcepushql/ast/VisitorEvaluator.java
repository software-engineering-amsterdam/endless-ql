package org.uva.forcepushql.ast;


import org.uva.forcepushql.gui.JPanelGUI;
import org.uva.forcepushql.questions.Question;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;

public class VisitorEvaluator implements ASTVisitor {

    @Override
    public LinkedList<JPanel> visit(FormNode node) {
        LinkedList<JPanel> result = new LinkedList<JPanel>();
        LinkedList<Question> questions = new LinkedList<Question>();
        HashMap<String,JPanelGUI> conditions = new HashMap<String, JPanelGUI>();

        for (Node n: node.getQuestions()) {
            if (n instanceof ConditionalIfNode){
                String condition = ((ConditionalIfNode) n).getCondition().accept(this);
                JPanelGUI jPanelIf = n.accept(this);
                conditions.put(condition,jPanelIf);
                result.add(jPanelIf.getPanel());

            }
            else {
                questions.add(n.accept(this));
            }
        }

        JPanelGUI jPanelGUI = new JPanelGUI();
        jPanelGUI.createPanel(questions,0);
        JPanel jPanelForm = jPanelGUI.getPanel();

        if (!conditions.isEmpty()){
            conditions.forEach((c,o) -> jPanelGUI.getQuestion(c).attachObserver(o));
        }

        result.addFirst(jPanelForm);

        return result;
    }

    @Override
    public JPanelGUI visit(ConditionalIfNode node) {
        JPanelGUI jPanelGUI = new JPanelGUI();
        LinkedList<Question> questions = new LinkedList<Question>();
        for (Node n: node.getQuestions()) {
            questions.add(n.accept(this));
        }

        if (node.getAfter() != null) {
            node.getAfter().accept(this);
        }

        jPanelGUI.createPanel(questions,0);
        jPanelGUI.getPanel().setVisible(false);

        return jPanelGUI;
    }

    @Override
    public String visit(ConditionalIfElseNode node) {
        String result = "\nIf Condition: " + node.getCondition().accept(this) + " Questions: ";
        for (Node n: node.getQuestions()) {
            result += n.accept(this);
        }

        return result;
    }

    @Override
    public String visit(ConditionalElseNode node) {
        String result = "\nElse Conditional > Questions: ";
        for (Node n: node.getQuestions()) {
            result += n.accept(this);
        }

        return result;
    }

    public String visit(AdditionNode node)
    {
        return node.getLeft().accept(this) + " + " + node.getRight().accept(this);
    }

    public String visit(SubtractionNode node)
    {
        return node.getLeft().accept(this) + " - " + node.getRight().accept(this);
    }

    public String visit(MultiplicationNode node) {
        return node.getLeft().accept(this) + " * " + node.getRight().accept(this); }

    public String visit(DivisionNode node)
    {
        double divisor = Double.valueOf(node.getRight().accept(this));
        if (divisor != 0.0 || !String.valueOf(divisor).equals("0.0"))
        {
            return node.getLeft().accept(this) + " / " + node.getRight().accept(this);
        }else { throw new ArithmeticException("Division by zero."); }
    }

    @Override
    public String visit(AndNode node) {
        return node.getLeft().accept(this) + " && " + node.getRight().accept(this);
    }

    @Override
    public String visit(OrNode node) {
        return node.getLeft().accept(this) + " || " + node.getRight().accept(this);
    }

    @Override
    public String visit(LessNode node) {
        return node.getLeft().accept(this) + " < " + node.getRight().accept(this);
    }

    @Override
    public String visit(GreaterNode node) {
        return node.getLeft().accept(this) + " > " + node.getRight().accept(this);
    }

    @Override
    public String visit(EqualLessNode node) {
        return node.getLeft().accept(this) + " <= " + node.getRight().accept(this);
    }

    @Override
    public String visit(EqualGreaterNode node) {
        return node.getLeft().accept(this) + " >= " + node.getRight().accept(this);
    }

    @Override
    public String visit(NotEqualNode node) {
        return node.getLeft().accept(this) + " != " + node.getRight().accept(this);
    }

    @Override
    public String visit(IsEqualNode node) {
        return node.getLeft().accept(this) + " == " + node.getRight().accept(this);
    }


    public String visit(NegateNode node)
    {
        return "!" + node.getInnerNode().accept(this);
    }

    @Override
    public Question visit(QuestionNode node) {
        Question question;
        String label = visit((LabelNode)node.getLeft());
        String name = visit((NameNode)node.getCenter());
        String type = visit((TypeNode)node.getRight());
        if(type.equals("boolean")){
            question = new Radio(label,type,name);
        }

        else
            question = new Textbox(label,type,name);

        return question;

    }

    @Override
    public Question visit(QuestionAssignValueNode node) {
        Question question = node.getPrevious().accept(this);
        String expression = node.getExpression().accept(this);
        return question;
    }

    @Override
    public String visit(LabelNode node) {
        return node.getLabel().replaceAll("\"","" );
    }

    @Override
    public String visit(NameNode node) {
        return node.getName();
    }

    @Override
    public String visit(TypeNode node) {
        return node.getType();
    }

    @Override
    public String visit(Variable node) {
        return node.getName();
    }

    @Override
    public String visit(DecimalNode node) {
        return String.valueOf(node.getValue());
    }

    public String visit(NumberNode node){
        return String.valueOf(node.getValue());
    }



}
