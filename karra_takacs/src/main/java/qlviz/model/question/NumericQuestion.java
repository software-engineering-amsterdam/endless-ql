package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.numericExpressions.NumericExpression;

import java.math.BigDecimal;

public abstract class NumericQuestion extends Question {

    protected final NumericExpression valueExpression;
    protected BigDecimal value = BigDecimal.ZERO;

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public NumericExpression getValueExpression() {
        return valueExpression;
    }

    public NumericQuestion(String name, String text, QuestionType type, NumericExpression valueExpression, QLParser.QuestionContext context) {
        super(name, text, type, context);
        this.valueExpression = valueExpression;
    }


}

