package qlviz.gui.viewModel;

import qlviz.gui.renderer.QuestionBlockRenderer;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Function;

public class QuestionBlockViewModelImpl implements QuestionBlockViewModel, QuestionObserver {

    private final QuestionBlock model;
    private final QuestionBlockRenderer renderer;
    private final List<QuestionViewModel> questionViewModels;
    private final List<ConditionalBlockViewModel> conditionalBlockViewModels;

    public QuestionBlockViewModelImpl(QuestionBlock model,
                                      QuestionBlockRenderer renderer,
                                      Function<Question, QuestionViewModel> questionViewModelFactory,
                                      Function<ConditionalBlock, ConditionalBlockViewModel> conditionalViewModelFactory)
    {
        this.model = model;
        this.renderer = renderer;

        this.questionViewModels = new ArrayList<>();
        this.conditionalBlockViewModels = new ArrayList<>();

        for (Question question : model.getQuestions()) {
            QuestionViewModel viewModel = questionViewModelFactory.apply(question);
            questionViewModels.add(viewModel);
            viewModel.subscribeToPropertyChanged(this);
        }

        for (ConditionalBlock block : model.getBlocks()) {
            conditionalBlockViewModels.add(conditionalViewModelFactory.apply(block));
        }
    }

    @Override
    public void notifyValueChanged(QuestionViewModel source) {
        renderer.render(model);
    }
}
