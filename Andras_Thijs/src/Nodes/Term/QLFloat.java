package Nodes.Term;

import Nodes.Type;
import QLExceptions.TypeException;

public class QLFloat extends Term {
    private float value;

    public QLFloat(float value) {
        this.value = value;
    }

    @Override
    public float getFloat() { return value; }

    @Override
    public Type getType() { return Type.DECIMAL; }

    /**
     * Compares a QLFloat to another Term.
     * @param other the Term this QLFloat is being compared to.
     * @return the outcome of the equal comparison on the values of the Terms.
     * @throws TypeException when the other Term is not a QLFloat.
     */
    @Override
    public boolean isEqual(Term other) throws TypeException {
        return value == other.getFloat();
    }
}
