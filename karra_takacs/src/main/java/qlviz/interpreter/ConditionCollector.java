package qlviz.interpreter;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ConditionCollector {
    List<BooleanExpression> getConditions(Question question);
}

