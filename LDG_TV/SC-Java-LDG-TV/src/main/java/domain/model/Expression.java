package domain.model;

import domain.model.question.QuestionVariable;
import domain.model.question.QuestionVariableValue;

public class Expression extends QuestionVariableValue {

    private QuestionVariable leftHandOperand;
    private QuestionVariable rightHandOperand;
    private String operator;

    public  Expression(QuestionVariable leftHandOperand, QuestionVariable rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }

}
