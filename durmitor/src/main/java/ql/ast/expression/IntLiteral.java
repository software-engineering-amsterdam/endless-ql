package ql.ast.expression;

public class IntLiteral extends Literal<Integer> {

    private int value;
    
    public IntLiteral() { 
        this.value = 0;
    }
    
    public IntLiteral(String value) { 
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
