package ql.ast.type;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToBool;
import ql.gui.alternative.interfaces.Enumerable;
import ql.visitors.interfaces.TypeVisitor;

public class Bool extends Type implements Enumerable {

    private final BoolLiteral[] values;
    
    public Bool() {
        values = new BoolLiteral[2];
        values[0] = new BoolLiteral(true);
        values[1] = new BoolLiteral(false);
    }
    
    @Override
    public BoolLiteral[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean equals(Type t) {
        return t.isBoolean();
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    public BoolLiteral parse(Literal<?> value) {
        return (BoolLiteral) value.accept(new ToBool());
    }
    
    public static BoolLiteral parseBool(Literal<?> value) {
            return new Bool().parse(value);
    }
}
