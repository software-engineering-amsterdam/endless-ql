package Nodes.Operator;

import Nodes.Term.Boolean;

public class And extends Operator {
    public Boolean calculate (Boolean left, Boolean right) {
        return new Boolean(left.getBoolean() && right.getBoolean());
    }
}
