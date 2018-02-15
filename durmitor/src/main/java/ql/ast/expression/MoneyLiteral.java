package ql.ast.expression;

public class MoneyLiteral extends Literal<String> {

    private String value;
    
    public MoneyLiteral() { 
        this.value = "0.00";
    }
    
    public MoneyLiteral(String value) { 
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
