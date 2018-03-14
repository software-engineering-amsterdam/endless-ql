package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.DateQuestion;

import java.util.List;

public class DateQuestionViewModel extends BaseQuestionViewModel {
    public DateQuestionViewModel(DateQuestion question, List<BooleanExpressionViewModel> conditions) {
        super(question, conditions);
    }


    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
