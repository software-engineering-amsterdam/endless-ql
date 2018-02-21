package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.model.BinaryBooleanOperator;

public class BinaryBooleanOperatorVisitor implements BinaryBooleanOperatorTranslator {

    @Override
    public BinaryBooleanOperator translate(String operatorString) {
        if (operatorString.equals("&&")) {
            return BinaryBooleanOperator.And;
        }
        else if (operatorString.equals("||")) {
            return BinaryBooleanOperator.Or;
        }
        throw new IllegalArgumentException("Accepted values for boolean operator are '&&' and '||'.");
    }
}
