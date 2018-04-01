package org.uva.forcepushql.parser.ast.visitors;

import org.uva.forcepushql.parser.ast.ValueType;
import org.uva.forcepushql.parser.ast.elements.*;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;
import org.uva.forcepushql.interpreter.gui.JPanelGUI;
import org.uva.forcepushql.interpreter.gui.questions.Question;

import javax.swing.*;
import java.util.List;

public interface ASTVisitor{

    List<JPanel> visit(FormNode node);

    List<JPanelGUI> visit(ConditionalNode node);

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
    String visit(NotNode node);

    Question visit(QuestionNode node);
    Question visit(QuestionAssignValueNode node);

    String visit(LabelNode node);
    String visit(NameNode node);
    ValueType visit(TypeNode node);
    String visit(VariableNode node);
    String visit(DecimalNode node);

}