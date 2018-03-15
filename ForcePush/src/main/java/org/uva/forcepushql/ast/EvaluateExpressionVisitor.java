package org.uva.forcepushql.ast;



public class EvaluateExpressionVisitor implements ASTVisitor {


    @Override
    public String visit(Node node) {
        if (node instanceof ExpressionNode) {
            return String.valueOf(visit((ExpressionNode) node));
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

        else if (node instanceof  QuestionNode){
            return visit((QuestionNode) node);
        }


        return "0.0";
    }

    @Override
    public String visit(FormNode node) {
        String result = "Name: " + node.getName();
        for (Node n: node.getQuestions()) {
            result += visit(n);
        }
        return result;
    }

    @Override
    public double visit(ExpressionNode node) {
        if (node instanceof AdditionNode)
        {
            return visit((AdditionNode) node);
        }
        else if(node instanceof NumberNode)
        {
            return visit((NumberNode) node);
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
        else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0.0;
        }

    }

    public double visit(AdditionNode node)
    {
        double result = Double.valueOf(visit(node.getLeft())) + Double.valueOf(visit(node.getRight()));
        return result;
    }

    public double visit(SubtractionNode node)
    {
        return Double.valueOf(visit(node.getLeft())) - Double.valueOf(visit(node.getRight()));
    }

    public double visit(MultiplicationNode node) {
        return Double.valueOf(visit(node.getLeft())) * Double.valueOf(visit(node.getRight())); }

    public double visit(DivisionNode node)
    {
        double divisor = Double.valueOf(visit(node.getRight()));
        if (divisor != 0.0)
        {
        return Double.valueOf(visit(node.getLeft())) / Double.valueOf(visit(node.getRight()));
        }else { throw new ArithmeticException("Division by zero."); }
    }

    public double visit(NegateNode node)
    {
        return -(Double.valueOf(visit(node.getInnerNode())));
    }

    @Override
    public String visit(QuestionNode node) {
        return "\n--> " + visit(node.getLeft()) + visit(node.getCenter()) + visit(node.getRight());

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

    public double visit(NumberNode node){
        return node.getValue();
    }

    public double visit(InfixExpressionNode node)
    {
        return Double.valueOf(visit(node.getLeft()));
    }


}
