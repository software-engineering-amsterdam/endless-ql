package Nodes.Operator;

import Nodes.Term.Term;
import Nodes.Term.Float;

public class Addition extends Operator {
    public Term calculate(Float left, Float right) {
        return new Float(left.getFloat() + right.getFloat());
    }
}
