package Nodes.Operator;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

public class Not extends Operator {
    public Not(String value) {
        super(value);
    }

    public QLBoolean calculate(Term left, Term right) throws TypeException, SyntaxException {
        if(!(right instanceof QLBoolean))
            throw new TypeException(this);

        if(this.getValue().equals("!")) {
            return new QLBoolean(!right.getBoolean());
        } else {
            throw new SyntaxException("Invalid not operator", this);
        }
    }
}
