package Nodes.Operator;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

public class Not extends Operator {
    /**
     * Constructor that just calls the default constructor.
     * @param value the not operator.
     */
    public Not(String value) {
        super(value);
    }

    /**
     * Implements a not operator (>=, <=, > and <).
     * @param left left hand side of the Operator. In this case it will be the same as right, but it won't be used.
     * @param right right hand side of the operator.
     * @return A new intermediary QLBoolean Term with the result of the calculation.
     * @throws TypeException when right is not a QLBoolean.
     * @throws SyntaxException when the value of this Operator is not "!".
     */
    public QLBoolean calculate(Term left, Term right) throws TypeException, SyntaxException {
        // Apply the correct implementation. Throw a SyntaxException for invalid operators (eg. not "!").
        // When right is not a QLBoolean, a TypeException will be thrown.
        if(this.getValue().equals("!"))
            return new QLBoolean(!right.getBoolean());
        else
            throw new SyntaxException("Invalid not operator", this);
    }
}
