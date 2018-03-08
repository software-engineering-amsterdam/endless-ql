package org.uva.forcepushql.ast;


public class EvaluateExpressionVisitor extends ASTVisitor<Double> {

    public double Visit(AdditionNode node)
    {
        double result = Visit(node.Left) + Visit(node.Right);
        System.out.println("Final result = " + result);
        return result;
    }

    public double Visit(SubtractionNode node)
    {
        return Visit(node.Left) - Visit(node.Right);
    }

    public double Visit(MultiplicationNode node)
    {
        return Visit(node.Left) * Visit(node.Right);
    }

    public double Visit(DivisionNode node)
    {
        return Visit(node.Left) / Visit(node.Right);
    }

    public double Visit(NegateNode node)
    {
        return -Visit(node.InnerNode);
    }

    public double Visit(NumberNode node){
        return node.Value;
    }


}
