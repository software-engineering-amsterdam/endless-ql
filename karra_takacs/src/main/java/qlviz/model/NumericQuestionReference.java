package qlviz.model;

import java.math.BigDecimal;

public class NumericQuestionReference extends NumericExpression {

    private NumericQuestion question;
    private final String questionName;

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
}
