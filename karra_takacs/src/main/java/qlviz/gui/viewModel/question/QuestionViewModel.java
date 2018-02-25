package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.propertyEvents.NotifyPropertyChanged;

public interface QuestionViewModel extends NotifyPropertyChanged<QuestionViewModel> {
    void accept(QuestionViewModelVisitor visitor);
    String getText();
}


