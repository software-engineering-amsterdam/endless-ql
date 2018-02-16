package ql.ast.expression;

public class StrLiteral extends Literal<String> {

    private String value;
    
    public StrLiteral() { 
        this.value = "";
    }
    
    public StrLiteral(String value) { 
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
