package qlviz.gui.viewModel.question;

import qlviz.model.question.MoneyQuestion;

public class MoneyQuestionViewModel extends NumericQuestionViewModel {

    public MoneyQuestionViewModel(MoneyQuestion question) {
        super(question);
    }


    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

}
