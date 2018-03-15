package Nodes.Operator;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;

public class Not extends Operator {
    public Not(String value) {
        super(value);
    }

    public QLBoolean calculate(Term left, QLBoolean right) {
        return new QLBoolean(!right.getBoolean());
    }
}
