package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.List;

public interface QuestionBlockViewModel {
    List<QuestionViewModel> getQuestions();

    List<ConditionalBlockViewModel> getBlocks();
}
