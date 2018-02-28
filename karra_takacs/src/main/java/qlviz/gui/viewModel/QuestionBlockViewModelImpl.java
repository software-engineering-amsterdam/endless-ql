package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QuestionBlockViewModelImpl implements QuestionBlockViewModel {

    private final QuestionBlock model;
    private final List<QuestionViewModel> questionViewModels;
    private final List<ConditionalBlockViewModel> conditionalBlockViewModels;

    public QuestionBlockViewModelImpl(QuestionBlock model,
                                      Function<Question, QuestionViewModel> questionViewModelFactory,
                                      Function<ConditionalBlock, ConditionalBlockViewModel> conditionalViewModelFactory)
    {
        this.model = model;

        this.questionViewModels = new ArrayList<>();
        this.conditionalBlockViewModels = new ArrayList<>();

        for (Question question : model.getQuestions()) {
            QuestionViewModel viewModel = questionViewModelFactory.apply(question);
            questionViewModels.add(viewModel);
        }

        for (ConditionalBlock block : model.getBlocks()) {
            ConditionalBlockViewModel viewModel = conditionalViewModelFactory.apply(block);
            conditionalBlockViewModels.add(viewModel);
        }
    }


    @Override
    public List<QuestionViewModel> getQuestions() {
        return this.questionViewModels;
    }

    @Override
    public List<ConditionalBlockViewModel> getBlocks() {
        return this.conditionalBlockViewModels;
    }


}
