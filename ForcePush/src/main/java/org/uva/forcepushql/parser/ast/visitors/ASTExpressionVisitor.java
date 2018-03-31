package org.uva.forcepushql.parser.ast.visitors;

import org.uva.forcepushql.parser.ast.elements.NumberNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;

public interface ASTExpressionVisitor {

    double visit(AdditionNode node);
    double visit(SubtractionNode node);
    double visit(MultiplicationNode node);
    double visit(DivisionNode node);
    boolean visit(AndNode node);
    boolean visit(OrNode node);
    boolean visit(LessNode node);
    boolean visit(GreaterNode node);
    boolean visit(EqualLessNode node);
    boolean visit(EqualGreaterNode node);
    boolean visit(NotEqualNode node);
    boolean visit(IsEqualNode node);
    boolean visit(NotNode node);

    double visit(NumberNode node);
    boolean visit(VariableNode node);
    double visit(DecimalNode node);
}
