package qlviz.gui.viewModel.question;

import qlviz.model.question.NumericQuestion;

import java.math.BigDecimal;

public abstract class NumericQuestionViewModel extends BaseQuestionViewModel {

    private final NumericQuestion question;

    protected NumericQuestionViewModel(NumericQuestion question) {
        super(question);
        this.question = question;
    }

    public BigDecimal getValue() {
        return question.getValue();
    }
}
