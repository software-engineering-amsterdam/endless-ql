package Nodes.Operator;

import Nodes.Term.Float;

public class Subtraction extends Operator {
    public Float calculate(Float left, Float right) {
        return new Float(left.getFloat() - right.getFloat());
    }
}
