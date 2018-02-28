package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

import java.util.function.Function;

public class QuestionBlockViewModelFactory {

    private final Function<Question, QuestionViewModel> questionFactory;

    public QuestionBlockViewModelFactory(Function<Question, QuestionViewModel> questionFactory) {
        this.questionFactory = questionFactory;
    }

    public QuestionBlockViewModel create(QuestionBlock block) {
        return new QuestionBlockViewModelImpl(block, questionFactory,
                conditionalBlock -> new ConditionalBlockViewModelImpl(conditionalBlock, this::create));
    }
}
