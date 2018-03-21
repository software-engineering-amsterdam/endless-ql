package Nodes.Term;

import Nodes.Type;
import QLExceptions.TypeException;

public class QLBoolean extends Term {
    private boolean value;

    public QLBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getBoolean() { return value; }

    @Override
    public Type getType() { return Type.BOOL; }

    /**
     * Compares a QLBoolean to another Term.
     * @param other the Term this QLBoolean is being compared to.
     * @return the outcome of the equal comparison on the values of the Terms.
     * @throws TypeException when the other Term is not a QLBoolean.
     */
    @Override
    public boolean isEqual(Term other) throws TypeException {
        return value == other.getBoolean();
    }
}
