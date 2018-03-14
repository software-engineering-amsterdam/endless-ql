package org.uva.forcepushql.ast;



public class EvaluateExpressionVisitor implements ASTVisitor {


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
        double result = visit(node.Left) + visit(node.Right);
        return result;
    }

    public double visit(SubtractionNode node)
    {
        return visit(node.Left) - visit(node.Right);
    }

    public double visit(MultiplicationNode node) { return visit(node.Left) * visit(node.Right); }

    public double visit(DivisionNode node)
    {
        double divisor = visit(node.Right);
        if (divisor != 0.0)
        {
        return visit(node.Left) / visit(node.Right);
        }else { throw new ArithmeticException("Division by zero."); }
    }

    public double visit(NegateNode node)
    {
        return -visit(node.getInnerNode());
    }

    public double visit(NumberNode node){
        return node.getValue();
    }

    public double visit(InfixExpressionNode node)
    {
        return visit(node.Left);
    }


}
