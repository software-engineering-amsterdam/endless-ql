package Nodes.Operator;

import Nodes.Term.*;
import QLExceptions.*;

public class ComparisonOperation extends Operator{
    /**
     * Constructor that just calls the default constructor.
     * @param value the comparison operator.
     */
    public ComparisonOperation(String value) {
        super(value);
    }

    /**
     * Implements a comparison operator (>=, <=, > and <).
     * @param left left hand side of the Operator
     * @param right right hand side of the operator
     * @return A new intermediary QLBoolean Term with the result of the calculation.
     * @throws TypeException when left and right aren't Floats.
     * @throws SyntaxException when the value of this Operator is not >=, <=, > or <.
     */
    public QLBoolean calculate(Term left, Term right) throws TypeException, SyntaxException {
        // Apply the correct implementation. Throw a SyntaxException for invalid operators.
        // When either left or right is not a QLFloat, a TypeException will be thrown.
        switch(this.getValue()) {
            case ">=": return new QLBoolean(left.getFloat() >= right.getFloat());
            case "<=": return new QLBoolean(left.getFloat() <= right.getFloat());
            case ">": return new QLBoolean(left.getFloat() > right.getFloat());
            case "<": return new QLBoolean(left.getFloat() < right.getFloat());
            default: throw new SyntaxException("Invalid comparison operator", this);
        }
    }
}
