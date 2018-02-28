package qlviz.gui.viewModel;

import qlviz.gui.viewModel.propertyEvents.NotifyPropertyChanged;
import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.List;

public interface QuestionBlockViewModel extends NotifyPropertyChanged<QuestionViewModel> {
    List<QuestionViewModel> getQuestions();

    List<ConditionalBlockViewModel> getBlocks();
}
