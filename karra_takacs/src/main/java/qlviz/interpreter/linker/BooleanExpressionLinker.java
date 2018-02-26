package qlviz.interpreter.linker;

import qlviz.model.NumericComparison;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.BooleanQuestionReference;

import java.util.HashMap;
import java.util.Map;

public class BooleanExpressionLinker implements BooleanExpressionVisitor {

    private final Map<String, BooleanQuestion> questions;

    public BooleanExpressionLinker(Map<String, BooleanQuestion> questions) {
        this.questions = questions;
    }


    @Override
    public void visit(BinaryBooleanOperation binaryBooleanOperation) {
       binaryBooleanOperation.getLeftSide().accept(this);
       binaryBooleanOperation.getRightSide().accept(this);
    }

    @Override
    public void visit(BooleanLiteral literal) {
        return;
    }

    @Override
    public void visit(Negation negation) {
        negation.getOperand().accept(this);
    }

    @Override
    public void visit(BooleanQuestionReference booleanQuestionReference) {
        booleanQuestionReference.setQuestion(this.questions.get(booleanQuestionReference.getQuestionName()));
    }

    @Override
    public void accept(NumericComparison numericComparison) {
        return;
    }
}
