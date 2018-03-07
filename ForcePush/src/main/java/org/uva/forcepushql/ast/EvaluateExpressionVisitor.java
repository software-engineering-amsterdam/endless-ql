package org.uva.forcepushql.ast;


public class EvaluateExpressionVisitor extends ASTVisitor<Double> {

    public double Visit(AdditionNode node) {
        return Visit(node.Left) + Visit(node.Right);
    }
    public double Visit(SubtractionNode node){
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
}
