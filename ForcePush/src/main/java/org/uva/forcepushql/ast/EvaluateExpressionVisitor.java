package org.uva.forcepushql.ast;



public class EvaluateExpressionVisitor implements ASTVisitor {

    @Override
    public String visit(FormNode node) {
        String result = "Name: " + node.getName();
        for (Node n: node.getQuestions()) {
            result += n.accept(this);
        }
        return result;
    }

    @Override
    public String visit(ConditionalIfNode node) {
        String result = "\nIf Condition: " + node.getCondition().accept(this) + " Questions: ";
        for (Node n: node.getQuestions()) {
            result += n.accept(this);
        }

        if (node.getAfter() != null) {
            result += node.getAfter().accept(this);
        }

        return result;
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
    public String visit(QuestionNode node) {
        return "\n--> " + node.getLeft().accept(this) + node.getCenter().accept(this) + node.getRight().accept(this);

    }

    @Override
    public String visit(QuestionAssignValueNode node) {
        return node.getPrevious().accept(this) + " = " + node.getExpression().accept(this);
    }

    @Override
    public String visit(LabelNode node) {
        return "Question: " + node.getLabel() + "; ";
    }

    @Override
    public String visit(NameNode node) {
        return "Variable: " + node.getName() + "; ";
    }

    @Override
    public String visit(TypeNode node) {
        return "Type: " + node.getType() + ";";
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
