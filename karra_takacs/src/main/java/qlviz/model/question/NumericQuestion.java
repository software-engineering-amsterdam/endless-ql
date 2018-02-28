package qlviz.model.question;

import qlviz.model.numericExpressions.NumericExpression;

import java.math.BigDecimal;

public abstract class NumericQuestion extends Question {

    protected final NumericExpression valueExpression;
    protected BigDecimal value = BigDecimal.ZERO;

    public BigDecimal getValue() {
        if (this.valueExpression == null)
        {
            return value;
        }
        else
        {
            return this.valueExpression.evaluate();
        }
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public NumericQuestion(String name, String text, QuestionType type, NumericExpression valueExpression) {
        super(name, text, type);
        this.valueExpression = valueExpression;
    }


}

