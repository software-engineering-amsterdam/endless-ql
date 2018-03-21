package org.uva.forcepushql.ast;

import org.uva.forcepushql.gui.JPanelGUI;
import org.uva.forcepushql.questions.Question;

import javax.swing.*;
import java.util.LinkedList;

public interface ASTVisitor{

    LinkedList<JPanel> visit(FormNode node);

    JPanelGUI visit(ConditionalIfNode node);
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

    Question visit(QuestionNode node);
    Question visit(QuestionAssignValueNode node);
    String visit(LabelNode node);
    String visit(NameNode node);
    String visit(TypeNode node);
    String visit(Variable node);
    String visit(DecimalNode node);

}