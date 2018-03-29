package qlviz.interpreter.linker;

import qlviz.model.expressions.numericExpressions.BinaryNumericOperation;
import qlviz.model.expressions.numericExpressions.NumericLiteral;
import qlviz.model.expressions.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestionReference;

public interface NumericExpressionVisitor {
    void visit(BinaryNumericOperation binaryNumericOperation);
    void visit(NumericLiteral numericLiteral);
    void visit(NumericNegation numericNegation);
    void visit(NumericQuestionReference numericQuestionReference);
}


