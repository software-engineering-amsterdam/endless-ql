package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Dec extends ASTNode  {
    private double value;

    public Dec(String value) {
        this.value = Double.parseDouble(value);
    }

    public Dec(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("decimal");
    }
}
