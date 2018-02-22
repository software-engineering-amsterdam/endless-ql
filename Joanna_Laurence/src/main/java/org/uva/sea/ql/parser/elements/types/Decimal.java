package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitor;

public class Decimal extends ASTNode  {
    private double value;

    public Decimal(String value) {
        this.value = Double.parseDouble(value);
    }

    public Decimal(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("decimal");
    }
}
