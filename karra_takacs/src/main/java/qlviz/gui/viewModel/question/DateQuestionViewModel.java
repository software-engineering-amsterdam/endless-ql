package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.DateQuestion;

public class DateQuestionViewModel extends BaseQuestionViewModel {
    public DateQuestionViewModel(DateQuestion question) {
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
