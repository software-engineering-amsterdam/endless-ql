package QL.Evaluation;

public abstract class Evaluator {
    public abstract Value evaluate();

    public Boolean isArithmetic(){ return false; }
    public Boolean isLogical(){ return false; }
}
