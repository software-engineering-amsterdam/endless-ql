package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.MoneyQuestion;

public class MoneyQuestionViewModel extends BaseQuestionViewModel {
    public MoneyQuestionViewModel(MoneyQuestion question) {
        super(question);
    }

    @Override
    public void subscribeToPropertyChanged(QuestionObserver observer) {

    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
