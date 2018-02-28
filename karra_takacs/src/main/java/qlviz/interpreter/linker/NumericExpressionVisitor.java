package qlviz.interpreter.linker;

import qlviz.model.numericExpressions.BinaryNumericOperation;
import qlviz.model.numericExpressions.NumericLiteral;
import qlviz.model.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestionReference;

public interface NumericExpressionVisitor {
    void visit(BinaryNumericOperation binaryNumericOperation);
    void visit(NumericLiteral numericLiteral);
    void visit(NumericNegation numericNegation);
    void visit(NumericQuestionReference numericQuestionReference);
}
