package qlviz.interpreter.linker;

import qlviz.model.numericExpressions.BinaryNumericOperation;
import qlviz.model.numericExpressions.NumericLiteral;
import qlviz.model.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestionReference;

public interface TypedNumericExpressionVisitor<T> {
    T visit(BinaryNumericOperation binaryNumericOperation);
    T visit(NumericLiteral numericLiteral);
    T visit(NumericNegation numericNegation);
    T visit(NumericQuestionReference numericQuestionReference);
}
