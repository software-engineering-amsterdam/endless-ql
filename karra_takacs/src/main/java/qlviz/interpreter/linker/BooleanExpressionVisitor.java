package qlviz.interpreter.linker;

import qlviz.model.booleanExpressions.NumericComparison;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.question.BooleanQuestionReference;

public interface BooleanExpressionVisitor {
    void visit(BinaryBooleanOperation binaryBooleanOperation);
    void visit(BooleanLiteral literal);
    void visit(Negation negation);
    void visit(BooleanQuestionReference booleanQuestionReference);
    void accept(NumericComparison numericComparison);
}

