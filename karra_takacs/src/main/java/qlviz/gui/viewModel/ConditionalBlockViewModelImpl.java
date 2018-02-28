package qlviz.gui.viewModel;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConditionalBlockViewModelImpl implements ConditionalBlockViewModel
{

    private final BooleanExpressionViewModel condition;
    private final List<QuestionBlockViewModel> questionBlocks;

    public ConditionalBlockViewModelImpl(
            ConditionalBlock conditionalBlock,
            Function<QuestionBlock, QuestionBlockViewModel> questionBlockQuestionBlockViewModelFactory,
            Function<BooleanExpression, BooleanExpressionViewModel> viewModelFactory) {
        this.condition = viewModelFactory.apply(conditionalBlock.getCondition());
        this.questionBlocks =
                conditionalBlock.getQuestionBlocks()
                        .stream()
                        .map(questionBlockQuestionBlockViewModelFactory)
                        .collect(Collectors.toList());
    }

    @Override
    public BooleanExpressionViewModel getCondition() {
        return this.condition;
    }

    @Override
    public List<QuestionBlockViewModel> getQuestionBlocks() {
        return this.questionBlocks;
    }

}
