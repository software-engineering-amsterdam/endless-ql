package Nodes.Term;

import Nodes.Expression;

public abstract class Term extends Expression {
    @Override
    // Return when Expression.getValue() is called with a Term on one side.
    public Term getValue() { return this; }

    @Override
    public String toString() {
        return this.toString().toLowerCase();
    }
}
