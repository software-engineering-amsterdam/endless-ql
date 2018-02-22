package domain.model;

public class Expression extends Variable {

    private String leftHandOperand;
    private String rightHandOperand;
    private String operator;

    public  Expression(String leftHandOperand, String rightHandOperand, String operator){
        this.leftHandOperand = leftHandOperand;
        this.rightHandOperand = rightHandOperand;
        this.operator = operator;
    }

    public String getLeftHandOperand() {
        return leftHandOperand;
    }

    public void setLeftHandOperand(String leftHandOperand) {
        this.leftHandOperand = leftHandOperand;
    }

    public String getRightHandOperand() {
        return rightHandOperand;
    }

    public void setRightHandOperand(String rightHandOperand) {
        this.rightHandOperand = rightHandOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
