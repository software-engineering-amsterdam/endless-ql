package org.uva.forcepushql.ast;

public interface ASTVisitor{

    String visit(FormNode node);

    String visit(ConditionalIfNode node);
    String visit(ConditionalIfElseNode node);
    String visit(ConditionalElseNode node);

    String visit(AdditionNode node);
    String visit(SubtractionNode node);
    String visit(NumberNode node);
    String visit(MultiplicationNode node);
    String visit(DivisionNode node);
    String visit(AndNode node);
    String visit(OrNode node);
    String visit(LessNode node);
    String visit(GreaterNode node);
    String visit(EqualLessNode node);
    String visit(EqualGreaterNode node);
    String visit(NotEqualNode node);
    String visit(IsEqualNode node);
    String visit(NegateNode node);

    String visit(QuestionNode node);
    String visit(QuestionAssignValueNode node);
    String visit(LabelNode node);
    String visit(NameNode node);
    String visit(TypeNode node);
    String visit(Variable node);
    String visit(DecimalNode node);

}