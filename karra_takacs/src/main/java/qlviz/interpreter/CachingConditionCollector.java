package qlviz.interpreter;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CachingConditionCollector implements ConditionCollector {

    private final HashMap<String, List<BooleanExpression>> conditionCache;

    public CachingConditionCollector(Form form) {
        conditionCache = new HashMap<>();
        this.collect(form);
    }

    private void collect(Form form) {
        for (QuestionBlock block : form.getQuestions()) {
            this.collect(block, new ArrayList<>());
        }
    }

    private void collect(ConditionalBlock block, List<BooleanExpression> conditionStack) {
        List<BooleanExpression> currentStack = new ArrayList<>(conditionStack);
        conditionStack.add(block.getCondition());
        for (QuestionBlock questionBlock : block.getQuestionBlocks()) {
            this.collect(questionBlock, conditionStack);
        }
    }

    private void collect(QuestionBlock block, List<BooleanExpression> conditionStack) {
        for (Question question : block.getQuestions()) {
            this.conditionCache.put(question.getName(), new ArrayList<>(conditionStack));
        }
        for (ConditionalBlock conditionalBlock : block.getBlocks()) {
            this.collect(conditionalBlock, conditionStack);
        }
    }

    @Override
    public List<BooleanExpression> getConditions(Question question) {
        return this.conditionCache.get(question.getName());
    }
}
