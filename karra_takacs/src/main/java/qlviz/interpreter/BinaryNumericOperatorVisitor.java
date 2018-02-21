package qlviz.interpreter;

import qlviz.model.BinaryNumericOperator;

public class BinaryNumericOperatorVisitor implements BinaryNumericOperatorTranslator {

    @Override
    public BinaryNumericOperator translate(String s) {
        if (s.equals("+")) {
            return BinaryNumericOperator.Add;
        }
        else if (s.equals("-")) {
            return BinaryNumericOperator.Subtract;
        }
        else if (s.equals("*")) {
            return BinaryNumericOperator.Multiply;
        }
        else if (s.equals("/")) {
            return BinaryNumericOperator.Divide;
        }
        return null;
    }
}
