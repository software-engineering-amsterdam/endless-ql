package Nodes.Term;

import Nodes.Expression;

public abstract class Term extends Expression {
    // Implement default behaviour for every term
    public float getFloat() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public boolean getBoolean() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public java.lang.String getString() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public Term getTerm() { throw new UnsupportedOperationException(); } // TODO: Change to some type error

    @Override
    // Return when Expression.getValue() is called with a Term on one side.
    public Term getValue() { return this; }

    @Override
    public java.lang.String toString() {
        return this.toString().toLowerCase();
    }
}
