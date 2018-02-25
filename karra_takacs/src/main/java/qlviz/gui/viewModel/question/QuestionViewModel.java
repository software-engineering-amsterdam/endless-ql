package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.Question;

public interface QuestionViewModel {
    void subscribeToPropertyChanged(QuestionObserver observer);
    void accept(QuestionViewModelVisitor visitor);
    String getText();
}


