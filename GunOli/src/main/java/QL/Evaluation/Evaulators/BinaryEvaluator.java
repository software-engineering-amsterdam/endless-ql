package QL.Evaluation.Evaulators;

import QL.Evaluation.Evaluator;
import QL.Evaluation.Value;

/**
 * Created by Michael on 3/29/2018.
 */
public abstract class BinaryEvaluator extends Evaluator {
    private Value leftValue;
    private Value rightValue;

    public BinaryEvaluator(Value leftValue, Value rightValue){
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public Value getLeftValue(){ return leftValue; }
    public Value getRightValue(){ return rightValue; }
}
