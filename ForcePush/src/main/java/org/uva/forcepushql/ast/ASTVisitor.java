package org.uva.forcepushql.ast;

public interface ASTVisitor{

    String visit(Node node);
    String visit(FormNode node);
    double visit(ExpressionNode node);
    double visit(AdditionNode node);
    double visit(NumberNode node);
    double visit(MultiplicationNode node);
    double visit(DivisionNode node);
    double visit(NegateNode node);
    String visit(QuestionNode node);
    String visit(LabelNode node);
    String visit(NameNode node);
    String visit(TypeNode node);

}