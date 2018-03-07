package qlviz.gui.viewModel.question;

import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuestionViewModel implements QuestionViewModel {

    private final Question question;

    protected BaseQuestionViewModel(Question question) {
        this.question = question;
    }

    public String getText() {
        return this.question.getText();
    }

    public String getName(){return this.question.getName();}

}
