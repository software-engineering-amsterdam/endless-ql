package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.BooleanQuestion;

public class BooleanQuestionViewModel extends BaseQuestionViewModel {
    public BooleanQuestionViewModel(BooleanQuestion question) {
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



