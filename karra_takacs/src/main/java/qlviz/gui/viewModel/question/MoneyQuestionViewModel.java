package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.MoneyQuestion;

import java.util.function.Function;

public class MoneyQuestionViewModel extends NumericQuestionViewModel {

    public MoneyQuestionViewModel(MoneyQuestion question, Function<NumericExpression, NumericExpressionViewModel> factory) {
        super(question, factory);
    }


    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

}
