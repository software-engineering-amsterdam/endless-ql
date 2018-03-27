package qlviz.interpreter.linker;

import qlviz.model.expressions.booleanExpressions.NumericComparison;
import qlviz.model.expressions.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.expressions.booleanExpressions.BooleanLiteral;
import qlviz.model.expressions.booleanExpressions.Negation;
import qlviz.model.question.BooleanQuestionReference;

public interface BooleanExpressionVisitor {
    void visit(BinaryBooleanOperation binaryBooleanOperation);
    void visit(BooleanLiteral literal);
    void visit(Negation negation);
    void visit(BooleanQuestionReference booleanQuestionReference);
    void visit(NumericComparison numericComparison);

}

