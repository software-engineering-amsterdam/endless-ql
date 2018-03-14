package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.MoneyQuestion;

import java.util.List;
import java.util.function.Function;

public class MoneyQuestionViewModel extends NumericQuestionViewModel {

    public MoneyQuestionViewModel(
            MoneyQuestion question,
            Function<NumericExpression,
            NumericExpressionViewModel> factory,
            List<BooleanExpressionViewModel> conditions) {
        super(question, factory, conditions);

    }


    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedQuestionViewModelVisitor<T> visitor) {
        return visitor.visit(this);
    }


}
