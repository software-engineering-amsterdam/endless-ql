package qlviz.interpreter.linker;

import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.booleanExpressions.NumericComparison;
import qlviz.model.question.BooleanQuestionReference;

public interface TypedBooleanExpressionVisitor<T> {
    T visit(BinaryBooleanOperation binaryBooleanOperation);
    T visit(BooleanLiteral literal);
    T visit(Negation negation);
    T visit(BooleanQuestionReference booleanQuestionReference);
    T visit(NumericComparison numericComparison);
}
