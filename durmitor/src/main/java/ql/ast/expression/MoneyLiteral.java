package ql.ast.expression;

import ql.ast.type.Money;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class MoneyLiteral extends Literal<String> {

    private String value;
    
    public MoneyLiteral() { 
        this.value = "0.00";
    }
    
    public MoneyLiteral(String value) { 
        this.value = value;
    }

    @Override
    public Type getType() {
        return new Money();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
