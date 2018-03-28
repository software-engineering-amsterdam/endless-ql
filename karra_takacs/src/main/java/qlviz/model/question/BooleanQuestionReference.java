package qlviz.model.question;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.TypedExpressionVisitor;

public class BooleanQuestionReference implements BooleanExpression {

    private BooleanQuestion question;
    private final String questionName;


    public BooleanQuestionReference(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
       return visitor.visit(this);
    }

    public BooleanQuestion getQuestion() {
        return question;
    }

    public void setQuestion(BooleanQuestion question) {
        this.question = question;
    }

    public String getQuestionName() {
        return questionName;
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


