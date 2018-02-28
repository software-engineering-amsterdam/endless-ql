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
    public void trySetValue(String input) {
        try {
            BigDecimal value = new BigDecimal(input);
            this.question.setValue(value);
            super.notifyPropertyChanged();
        }
        catch (NumberFormatException e) {
            return;
        }
    }
}
