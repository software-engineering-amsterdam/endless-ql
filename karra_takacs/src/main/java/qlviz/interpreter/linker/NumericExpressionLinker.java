package qlviz.interpreter.linker;

import qlviz.model.expressions.numericExpressions.BinaryNumericOperation;
import qlviz.model.expressions.numericExpressions.NumericLiteral;
import qlviz.model.expressions.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestion;
import qlviz.model.question.NumericQuestionReference;

import java.util.Map;

public class NumericExpressionLinker implements NumericExpressionVisitor {

    private final Map<String, NumericQuestion> questions;

    public NumericExpressionLinker(Map<String, NumericQuestion> questions) {
        this.questions = questions;
    }


    @Override
    public void visit(BinaryNumericOperation binaryNumericOperation) {
       binaryNumericOperation.getLeftSide().accept(this);
       binaryNumericOperation.getRightSide().accept(this);
    }

    @Override
    public void visit(NumericLiteral literal) {
        return;
    }

    @Override
    public void visit(NumericQuestionReference numericQuestionReference) {
        numericQuestionReference.setQuestion(this.questions.get(numericQuestionReference.getQuestionName()));
    }

    @Override
    public void visit(NumericNegation numericNegation) {
        numericNegation.getInnerExpression().accept(this);
    }
}
