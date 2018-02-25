package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.DecimalQuestion;

public class DecimalQuestionViewModel extends BaseQuestionViewModel {
    public DecimalQuestionViewModel(DecimalQuestion question) {
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
