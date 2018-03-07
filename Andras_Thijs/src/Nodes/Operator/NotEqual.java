package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Float;
import Nodes.Term.QLString;

public class NotEqual extends Operator {
    public Boolean calculate(Boolean left, Boolean right) {
        return new Boolean(left.getBoolean() != right.getBoolean());
    }

    public Boolean calculate(Float left, Float right) {
        return new Boolean(left.getFloat() != right.getFloat());
    }

    public Boolean calculate(QLString left, QLString right) {
        return new Boolean(left.getString() != right.getString());
    }
}
