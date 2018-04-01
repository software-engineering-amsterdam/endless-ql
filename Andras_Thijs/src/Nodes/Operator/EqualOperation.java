package Nodes.Operator;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

public class EqualOperation extends Operator {
    /**
     * Constructor that just calls the default constructor.
     * @param value the equal operator.
     */
    public EqualOperation(String value) {
        super(value);
    }

    /**
     * Implements an equal operator (== and !=).
     * @param left left hand side of the operator.
     * @param right right hand side of the operator.
     * @return A new intermediary QLBoolean Term with the result of the calculation.
     * @throws TypeException when left and right aren't the same Type.
     * @throws SyntaxException when the value of this Operator is not == or !=.
     */
    @Override
    public QLBoolean calculate(Term left, Term right) throws TypeException, SyntaxException {
        // Apply the correct implementation. Throw a SyntaxException for invalid operators.
        // isEqual is implemented by every Term, and throws an TypeException when the Types aren't the same.
        // This also means that Integers, Floats and Money can be equal, and Strings and Dates can be equal.
        switch(this.getValue()) {
            case "==": return new QLBoolean(left.isEqual(right));
            case "!=": return new QLBoolean(!left.isEqual(right));
            default: throw new SyntaxException("Invalid equal operator", this);
        }
    }
}
