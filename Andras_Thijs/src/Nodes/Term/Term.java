package Nodes.Term;

import Nodes.Expression;

public abstract class Term extends Expression {
    @Override
    // Return when Expression.getTerm() is called with a Term on one side.
    public Term getTerm() { return this; }

    @Override
    public String toString() {
        return this.toString().toLowerCase();
    }

    public float getValue(){
        return 0;
    }

}
