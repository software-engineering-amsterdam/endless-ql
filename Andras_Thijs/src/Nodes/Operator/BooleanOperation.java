package Nodes.Operator;

import Nodes.Term.QLBoolean;
import QLExceptions.SyntaxException;

public class BooleanOperation extends Operator {

    public BooleanOperation(String value){
        super(value);
    }

    // TODO Maybe use Term instead of QLBoolean and do typechecking inside the operator if it doesn't work this way.
    /**
     * Implements a QLBoolean operator (&& and ||)
     * @param left left hand side of the operator
     * @param right right hand side of the operator
     * @return A new intermediary QLBoolean term with the result of the calculation
     * @throws SyntaxException when the value of this Operator is not && or ||
     */
    public QLBoolean calculate(QLBoolean left, QLBoolean right) throws SyntaxException {
        if(this.getValue().equals("&&"))
            return new QLBoolean(left.getBoolean() && right.getBoolean());
        if(this.getValue().equals("||"))
            return new QLBoolean(left.getBoolean() && right.getBoolean());

        throw new SyntaxException("Invalid boolean operator", this);
    }
}
