package qlviz.interpreter;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.question.Question;

import java.util.List;

public interface ConditionCollector {
    List<BooleanExpression> getConditions(Question question);
}

