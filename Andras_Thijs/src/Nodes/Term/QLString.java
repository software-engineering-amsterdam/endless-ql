package Nodes.Term;

import Nodes.Type;
import QLExceptions.TypeException;

public class QLString extends Term {
    private String value;

    public QLString(String value) {
        this.value = value;
    }

    @Override
    public String getString() { return value; }

    @Override
    public Type getType() { return Type.STRING; }

    /**
     * Compares a QLString to another Term.
     * @param other the Term this QLString is being compared to.
     * @return the outcome of the equal comparison on the values of the Terms.
     * @throws TypeException when the other Term is not a QLString.
     */
    @Override
    public boolean isEqual(Term other) throws TypeException {
        return value.equals(other.getString());
    }
}
