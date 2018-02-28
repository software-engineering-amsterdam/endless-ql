package qlviz.gui.viewModel.question;

import qlviz.model.question.DecimalQuestion;

public class DecimalQuestionViewModel extends NumericQuestionViewModel {
    public DecimalQuestionViewModel(DecimalQuestion question) {
        super(question);
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
