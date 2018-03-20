package org.uva.forcepushql.ast;



public class EvaluateExpressionVisitor implements ASTVisitor {


    /*@Override
    public String visit(Node node) {
        if (node instanceof ExpressionNode) {
            return visit((ExpressionNode) node);
        }

        else if(node instanceof FormNode){
            return visit((FormNode)node);
        }

        else if (node instanceof LabelNode){
            return visit((LabelNode)node);
        }

        else if (node instanceof NameNode){
            return visit((NameNode)node);
        }

        else if (node instanceof TypeNode){
            return visit((TypeNode)node);
        }

        else if (node instanceof QuestionAssignValueNode){
            return visit((QuestionAssignValueNode)node);
        }

        else if (node instanceof  QuestionNode){
            return visit((QuestionNode) node);
        }

        else if (node instanceof ConditionalElseNode){
            return visit((ConditionalElseNode)node);
        }

        else if (node instanceof ConditionalIfNode) {
            return visit((ConditionalIfNode) node);
        }

        else if (node instanceof ConditionalIfElseNode){
            return visit((ConditionalIfElseNode)node);
        }


        return "0.0";
    }*/

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


    /*@Override
    public String visit(ExpressionNode node) {
        if (node instanceof AdditionNode)
        {
            return visit((AdditionNode) node);
        }
        else if(node instanceof NumberNode)
        {
            return visit((NumberNode) node);
        }
        else if (node instanceof Variable){
            return visit((Variable)node);
        }
        else if (node instanceof DecimalNode){
            return visit((DecimalNode)node);
        }
        else if(node instanceof SubtractionNode)
        {
            return visit((SubtractionNode) node);
        }
        else if(node instanceof MultiplicationNode)
        {
            return visit((MultiplicationNode) node);
        }
        else if(node instanceof NegateNode)
        {
            return visit((NegateNode) node);
        }
        else if(node instanceof DivisionNode)
        {
            return visit((DivisionNode) node);
        }
        else if(node instanceof AndNode)
        {
            return visit((AndNode) node);
        }
        else if(node instanceof OrNode)
        {
            return visit((OrNode) node);
        }
        else if(node instanceof LessNode)
        {
            return visit((LessNode) node);
        }
        else if(node instanceof GreaterNode)
        {
            return visit((GreaterNode) node);
        }
        else if(node instanceof EqualLessNode)
        {
            return visit((EqualLessNode) node);
        }
        else if(node instanceof EqualGreaterNode)
        {
            return visit((EqualGreaterNode) node);
        }
        else if(node instanceof NotEqualNode)
        {
            return visit((NotEqualNode) node);
        }
        else if(node instanceof IsEqualNode)
        {
            return visit((IsEqualNode) node);
        }
        else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "0.0";
        }

    }*/

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
