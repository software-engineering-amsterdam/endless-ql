package qlviz.gui.viewModel.propertyEvents;

import qlviz.gui.viewModel.question.QuestionViewModel;

public interface PropertyChangedListener<T> {
    void notifyValueChanged(T source);
}
