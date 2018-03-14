package Nodes.Operator;

import Nodes.Term.Term;
import Nodes.Term.Boolean;

public class Not extends Operator {

    public Not(String value) {
        super(value);
    }

    public Boolean calculate(Term left, Boolean right) {
        return new Boolean(!right.getBoolean());
    }
}
