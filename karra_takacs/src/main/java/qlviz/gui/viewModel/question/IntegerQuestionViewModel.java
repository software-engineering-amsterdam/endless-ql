package qlviz.gui.viewModel.question;

import qlviz.model.question.IntegerQuestion;

public class IntegerQuestionViewModel extends BaseQuestionViewModel {
    public IntegerQuestionViewModel(IntegerQuestion question) {
        super(question);
    }


    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
