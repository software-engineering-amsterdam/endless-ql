package Nodes.Operator;

import Nodes.Term.Float;

public class Exponent extends Operator {
    public Float calculate(Float left, Float right) {
        return new Float((float) Math.pow(left.getFloat(), right.getFloat()));
    }
}
