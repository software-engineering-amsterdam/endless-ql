package ql.evaluator;

import ql.ast.expression.literal.Literal;
import ql.visitors.interfaces.ValueVisitor;

public abstract class AbstractEvaluator<V extends Literal<?>> implements ValueVisitor {

    protected V firstOperand;
    
    public AbstractEvaluator (V firstOperand) {
        this.firstOperand   = firstOperand;
    }
}
