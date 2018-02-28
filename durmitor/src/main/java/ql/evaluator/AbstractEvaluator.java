package ql.evaluator;

import ql.evaluator.value.Value;
import ql.visitors.interfaces.ValueVisitor;

public abstract class AbstractEvaluator<V extends Value<?>> implements ValueVisitor {

    protected V firstOperand;
    
    public AbstractEvaluator (V firstOperand) {
        this.firstOperand   = firstOperand;
    }
}
