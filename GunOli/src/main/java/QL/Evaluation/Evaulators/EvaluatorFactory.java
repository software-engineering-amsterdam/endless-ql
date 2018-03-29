package QL.Evaluation.Evaulators;

import QL.Evaluation.Evaluator;
import QL.Evaluation.Evaulators.BinaryEvaluators.*;
import QL.Evaluation.Evaulators.UnaryEvaluators.NegationEvaluator;
import QL.Evaluation.Evaulators.UnaryEvaluators.NotEvaluator;
import QL.Evaluation.Value;

public class EvaluatorFactory {
    public Evaluator createBinaryEvaluator(String evaluatorType, Value left, Value right){
        switch(evaluatorType){
            case "ADD": return new AdditionEvaluator(left, right);
            case "AND": return new AndEvaluator(left, right);
            case "DIV": return new DivideEvaluator(left, right);
            case "EQ": return new EqualsEvaluator(left, right);
            case "GE": return new GreaterEqualEvaluator(left, right);
            case "GT": return new GreaterThanEvaluator(left, right);
            case "LE": return new LessEqualEvaluator(left, right);
            case "LT": return new LessThanEvaluator(left, right);
            case "MUL": return new MultiplyEvaluator(left, right);
            case "NEQ": return new NotEqualEvalutor(left, right);
            case "OR": return new OrEvaluator(left, right);
            case "SUB": return new SubtractionEvaluator(left, right);
            default: throw new IllegalArgumentException("Evaluator Type Invalid");
        }
    }

    public Evaluator createUnaryEvaluator(String evaluatorType, Value value){
        switch(evaluatorType){
            case "NEG": return new NegationEvaluator(value);
            case "NOT": return new NotEvaluator(value);
            default: throw new IllegalArgumentException("Evaluator Type Invalid");
        }
    }
}
