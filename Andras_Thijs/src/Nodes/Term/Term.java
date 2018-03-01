package Nodes.Term;

import Nodes.Expression;

public abstract class Term extends Expression {
    public float getFloat() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public boolean getBoolean() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public String getString() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
    public Term getValue() { throw new UnsupportedOperationException(); } // TODO: Change to some type error
}
