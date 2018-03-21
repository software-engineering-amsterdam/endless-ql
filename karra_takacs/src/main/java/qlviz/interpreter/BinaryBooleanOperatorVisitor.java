package qlviz.interpreter;

import com.google.inject.Inject;
import qlviz.model.booleanExpressions.BinaryBooleanOperator;

public class BinaryBooleanOperatorVisitor implements BinaryBooleanOperatorTranslator {

    @Inject
    public BinaryBooleanOperatorVisitor() {}

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
