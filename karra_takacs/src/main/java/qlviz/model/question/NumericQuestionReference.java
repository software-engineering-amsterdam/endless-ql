package qlviz.model.question;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.model.numericExpressions.NumericExpression;

import java.math.BigDecimal;

public class NumericQuestionReference extends NumericExpression {

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
    public BigDecimal evaluate() {
        if (this.question != null) {
            return this.question.getValue();
        }
        else
        {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }
}
