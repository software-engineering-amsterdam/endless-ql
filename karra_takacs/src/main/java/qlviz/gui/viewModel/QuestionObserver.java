package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.QuestionViewModel;

public interface QuestionObserver {
    void notifyValueChanged(QuestionViewModel source);
}
