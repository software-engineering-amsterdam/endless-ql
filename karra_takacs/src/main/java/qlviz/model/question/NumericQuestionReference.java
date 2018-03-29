package qlviz.model.question;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.TypedExpressionVisitor;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class NumericQuestionReference implements NumericExpression {

    private NumericQuestion question;
    private final String questionName;

    public String getQuestionName() {
        return questionName;
    }

    public NumericQuestion getQuestion() {
        return question;
    }

    public void setQuestion(NumericQuestion question) {
        this.question = question;
    }

    public NumericQuestionReference(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedExpressionVisitor<T> typedExpressionVisitor) {
        return typedExpressionVisitor.visit(this);
    }
}
