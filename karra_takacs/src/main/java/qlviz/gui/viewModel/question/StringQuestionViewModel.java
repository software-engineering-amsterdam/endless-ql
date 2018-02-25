package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.StringQuestion;

public class StringQuestionViewModel extends BaseQuestionViewModel {


    private final StringQuestion question;

    public StringQuestionViewModel(StringQuestion question) {
        super(question);
        this.question = question;
    }

    public String getValue() {
        return this.question.getValue();
    }

    @Override
    public void subscribeToPropertyChanged(QuestionObserver observer) {

    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
