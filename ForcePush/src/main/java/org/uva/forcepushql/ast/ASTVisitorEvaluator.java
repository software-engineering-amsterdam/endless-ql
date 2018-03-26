package org.uva.forcepushql.ast;


import org.uva.forcepushql.gui.JPanelGUI;
import org.uva.forcepushql.questions.Question;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;

public class ASTVisitorEvaluator implements ASTVisitor
{

    @Override
    public LinkedList<JPanel> visit(FormNode node)
    {
        LinkedList<JPanel>         result     = new LinkedList<>();
        LinkedList<Question>       questions  = new LinkedList<>();
        HashMap<String, JPanelGUI> conditions = new HashMap<>();
        LinkedList<String> variables = new LinkedList<>();
        HashMap<String,String> calculations = new HashMap<>();
        for (Node n : node.getQuestions())
        {
            if (n instanceof ConditionalIfNode)
            {
                String    condition = ((ConditionalIfNode) n).getCondition().accept(this);
                LinkedList<JPanelGUI> jPanelIf  = n.accept(this);
                jPanelIf.getFirst().setCondition(condition);
                condition = allTogether(condition);
                String[] names = condition.split("\\.");
                for (String s: names) {
                    conditions.put(s, jPanelIf.getFirst());
                }
                for (JPanelGUI jpg: jPanelIf) {
                    result.add(jpg.getPanel());
                }

            }

            else if(n instanceof  QuestionAssignValueNode){
                String calculation = ((QuestionAssignValueNode) n).getExpression().accept(this);
                Question question = ((QuestionAssignValueNode) n).accept(this);
                calculations.put(question.answerNameValue(),calculation);
                calculation = allTogether(calculation);
                String[] names = calculation.split("\\.");
                ((Textbox) question).setHasCalculation(true);

                for (String s: names) {
                    variables.add(s);
                }

                questions.add(question);

            }

            else
            {
                questions.add(n.accept(this));
            }
        }

        JPanelGUI jPanelGUI = new JPanelGUI();
        jPanelGUI.createPanel(questions, 0);
        JPanel jPanelForm = jPanelGUI.getPanel();

        if (!conditions.isEmpty())
        {
            conditions.forEach((c, o) -> jPanelGUI.getQuestion(c).attachObserver(o));
        }

        if(!variables.isEmpty()){
            variables.forEach(v -> jPanelGUI.getQuestion(v).attachObserver(jPanelGUI));
        }

        if (!calculations.isEmpty()){
            calculations.forEach((v,c) -> jPanelGUI.addCalculation(v,c));
        }

        result.addFirst(jPanelForm);

        return result;
    }

    @Override
    public LinkedList<JPanelGUI> visit(ConditionalIfNode node)
    {
        LinkedList<JPanelGUI> result = new LinkedList<>();
        JPanelGUI            jPanelGUI = new JPanelGUI();
        LinkedList<Question> questions = new LinkedList<Question>();
        HashMap<String, JPanelGUI> conditions = new HashMap<>();
        LinkedList<String> variables = new LinkedList<>();
        HashMap<String,String> calculations = new HashMap<>();
        for (Node n : node.getQuestions())
        {
            if (n instanceof ConditionalIfNode)
            {
                String    condition = ((ConditionalIfNode) n).getCondition().accept(this);
                LinkedList<JPanelGUI> jPanelIf  = n.accept(this);
                jPanelIf.getFirst().setCondition(condition);
                condition = allTogether(condition);
                String[] names = condition.split("\\.");
                for (String s: names) {
                    conditions.put(s, jPanelIf.getFirst());
                }
                for (JPanelGUI jpg: jPanelIf) {
                    result.add(jpg);
                }

            }
            else if(n instanceof  QuestionAssignValueNode){
                String calculation = ((QuestionAssignValueNode) n).getExpression().accept(this);
                Question question = ((QuestionAssignValueNode) n).accept(this);
                calculations.put(question.answerNameValue(),calculation);
                calculation = allTogether(calculation);
                String[] names = calculation.split("\\.");
                ((Textbox) question).setHasCalculation(true);

                for (String s: names) {
                   variables.add(s);
                }

                questions.add(question);

            }

            else {
                questions.add(n.accept(this));
            }
        }

        if (node.getAfter() != null)
        {
            if (node.getAfter() instanceof ConditionalIfNode){
                LinkedList<JPanelGUI> jPanelGUIS = node.getAfter().accept(this);

                for (JPanelGUI jpg: jPanelGUIS) {
                    result.add(jpg);
                }
            }
            else {
                result.add(node.getAfter().accept(this));
            }
        }

        jPanelGUI.createPanel(questions, 0);
        JPanel ifElsePanel = jPanelGUI.getPanel();
        ifElsePanel.setVisible(false);
        result.addFirst(jPanelGUI);

        if(!variables.isEmpty()){
            variables.forEach(v -> jPanelGUI.getQuestion(v).attachObserver(jPanelGUI));
        }

        if (!calculations.isEmpty()){
            calculations.forEach((v,c) -> jPanelGUI.addCalculation(v,c));
        }

        return result;
    }


    @Override
    public JPanelGUI visit(ConditionalElseNode node)
    {
        JPanelGUI            jPanelGUI = new JPanelGUI();
        LinkedList<Question> questions = new LinkedList<Question>();
        LinkedList<String> variables = new LinkedList<>();
        HashMap<String,String> calculations = new HashMap<>();
        for (Node n : node.getQuestions())
        {
            if(n instanceof  QuestionAssignValueNode){
                String calculation = ((QuestionAssignValueNode) n).getExpression().accept(this);
                Question question = ((QuestionAssignValueNode) n).accept(this);
                calculations.put(question.answerNameValue(),calculation);
                calculation = allTogether(calculation);
                String[] result = calculation.split("\\.");
                ((Textbox) question).setHasCalculation(true);

                for (String s: result) {
                    variables.add(s);
                }

                questions.add(question);

            }

            else {
                questions.add(n.accept(this));
            }
        }

        jPanelGUI.createPanel(questions, 0);
        jPanelGUI.getPanel().setVisible(false);

        if(!variables.isEmpty()){
            variables.forEach(v -> jPanelGUI.getQuestion(v).attachObserver(jPanelGUI));
        }

        if (!calculations.isEmpty()){
            calculations.forEach((v,c) -> jPanelGUI.addCalculation(v,c));
        }

        return jPanelGUI;
    }

    public String visit(AdditionNode node)
    {
        return node.getLeft().accept(this) + " + " + node.getRight().accept(this);
    }

    public String visit(SubtractionNode node)
    {
        return node.getLeft().accept(this) + " - " + node.getRight().accept(this);
    }

    public String visit(MultiplicationNode node)
    {
        return node.getLeft().accept(this) + " * " + node.getRight().accept(this);
    }

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
        String   label = visit((LabelNode) node.getLeft());
        String   name  = visit((NameNode) node.getCenter());
        String   type  = visit((TypeNode) node.getRight());

        if (type.equals("boolean"))
        {
            question = new Radio(label, type, name);
        } else
        {
            question = new Textbox(label, type, name);
        }
        return question;

    }

    @Override
    public Textbox visit(QuestionAssignValueNode node)
    {
        return node.getPrevious().accept(this);
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

    public String visit(NumberNode node)
    {
        return String.valueOf(node.getValue());
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
}
