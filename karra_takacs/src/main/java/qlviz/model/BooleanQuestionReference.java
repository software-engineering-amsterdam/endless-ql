package qlviz.model;

import java.math.BigDecimal;

public class BooleanQuestionReference extends BooleanExpression {

    private BooleanQuestion question;
    private final String questionName;


    public BooleanQuestionReference(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public boolean evaluate() {
        if (this.question != null) {
            return this.question.getValue();
        }
        else
        {
            return false;
        }
    }
}


