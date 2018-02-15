package ql.ast.expression;

public class BoolLiteral extends Literal<Boolean> {

    private boolean value;
    
    public BoolLiteral() { 
        this.value = false;
    }
    
    public BoolLiteral(String value) { 
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

}
