package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.propertyEvents.PropertyChangedListener;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuestionViewModel implements QuestionViewModel {

    private final Question question;
    private final List<PropertyChangedListener<QuestionViewModel>> listeners = new ArrayList<>();

    protected BaseQuestionViewModel(Question question) {
        this.question = question;
    }

    public String getText() {
        return this.question.getText();
    }

    protected void notifyPropertyChanged(){
        this.listeners.forEach(questionObserver -> questionObserver.notifyValueChanged(this));
    }

    @Override
    public void subscribeToPropertyChanged(PropertyChangedListener<QuestionViewModel> observer) {
        this.listeners.add(observer);
    }
}

