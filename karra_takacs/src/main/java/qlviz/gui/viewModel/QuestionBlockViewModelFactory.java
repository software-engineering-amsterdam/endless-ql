package qlviz.gui.viewModel;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.QuestionBlock;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.question.Question;

import java.util.function.Function;

public class QuestionBlockViewModelFactory {

    private final Function<Question, QuestionViewModel> questionFactory;
    private final Function<BooleanExpression, BooleanExpressionViewModel> booleanExpressionFactory;

    public QuestionBlockViewModelFactory(Function<Question, QuestionViewModel> questionFactory,
                                         Function<BooleanExpression, BooleanExpressionViewModel> booleanExpressionFactory) {
        this.questionFactory = questionFactory;
        this.booleanExpressionFactory = booleanExpressionFactory;
    }

    public QuestionBlockViewModel create(QuestionBlock block) {
        return new QuestionBlockViewModelImpl(block, questionFactory,
                conditionalBlock -> new ConditionalBlockViewModelImpl(conditionalBlock, this::create, booleanExpressionFactory));
    }
}
