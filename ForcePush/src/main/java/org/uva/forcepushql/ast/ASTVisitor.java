package org.uva.forcepushql.ast;

public interface ASTVisitor<T>{

    double visit(ExpressionNode node);
    double visit(AdditionNode node);
    double visit(NumberNode node);
    double visit(MultiplicationNode node);
    double visit(DivisionNode node);
    double visit(NegateNode node);

}