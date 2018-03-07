package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.StringQuestion;

import java.util.List;

public class StringQuestionViewModel extends BaseQuestionViewModel {


    private final StringQuestion question;

    public StringQuestionViewModel(StringQuestion question, List<BooleanExpressionViewModel> conditions) {
        super(question, conditions);
        this.question = question;
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
