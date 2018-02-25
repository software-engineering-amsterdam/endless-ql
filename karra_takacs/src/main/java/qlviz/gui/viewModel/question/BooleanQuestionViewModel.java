package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.QuestionObserver;
import qlviz.model.question.BooleanQuestion;

public class BooleanQuestionViewModel extends BaseQuestionViewModel {

    private final BooleanQuestion question;

    public BooleanQuestionViewModel(BooleanQuestion question) {
        super(question);
        this.question = question;
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public boolean getValue() {return question.getValue();}
    public void setValue(boolean value) {
        this.question.setValue(value);
        super.notifyPropertyChanged();
    }

}



