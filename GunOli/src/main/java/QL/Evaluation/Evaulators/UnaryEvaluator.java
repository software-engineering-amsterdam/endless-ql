package QL.Evaluation.Evaulators;

import QL.Evaluation.Evaluator;
import QL.Evaluation.Value;

public abstract class UnaryEvaluator extends Evaluator {
    private Value value;

    public UnaryEvaluator(Value value){
        this.value = value;
    }

    public Value getValue(){ return value; }
}
