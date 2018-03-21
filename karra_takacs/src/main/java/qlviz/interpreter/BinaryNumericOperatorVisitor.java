package qlviz.interpreter;

import com.google.inject.Inject;
import qlviz.model.numericExpressions.BinaryNumericOperator;

public class BinaryNumericOperatorVisitor implements BinaryNumericOperatorTranslator {

    @Inject
    public BinaryNumericOperatorVisitor() {}

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
