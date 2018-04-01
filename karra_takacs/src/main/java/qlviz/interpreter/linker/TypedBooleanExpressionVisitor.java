package qlviz.interpreter.linker;

import qlviz.model.expressions.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.expressions.booleanExpressions.BooleanLiteral;
import qlviz.model.expressions.booleanExpressions.Negation;
import qlviz.model.expressions.booleanExpressions.NumericComparison;
import qlviz.model.question.BooleanQuestionReference;

public interface TypedBooleanExpressionVisitor<T> {
    T visit(BinaryBooleanOperation binaryBooleanOperation);
    T visit(BooleanLiteral literal);
    T visit(Negation negation);
    T visit(BooleanQuestionReference booleanQuestionReference);
    T visit(NumericComparison numericComparison);
}
