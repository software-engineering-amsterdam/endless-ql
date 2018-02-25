package qlviz.gui.viewModel;

import qlviz.model.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConditionalBlockViewModelImpl implements ConditionalBlockViewModel {

    private final BooleanExpression condition;
    private final List<QuestionBlockViewModel> questionBlocks;

    public ConditionalBlockViewModelImpl(ConditionalBlock conditionalBlock, Function<QuestionBlock, QuestionBlockViewModel> questionBlockQuestionBlockViewModelFactory) {
        this.condition = conditionalBlock.getCondition();
        this.questionBlocks = conditionalBlock.getQuestionBlocks().stream().map(questionBlockQuestionBlockViewModelFactory).collect(Collectors.toList());
    }

    @Override
    public BooleanExpression getCondition() {
        return this.condition;
    }

    @Override
    public List<QuestionBlockViewModel> getQuestionBlocks() {
        return this.questionBlocks;
    }
}
