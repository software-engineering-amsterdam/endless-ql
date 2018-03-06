package qlviz.gui.viewModel.question;

import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.DecimalQuestion;

import java.util.function.Function;

public class DecimalQuestionViewModel extends NumericQuestionViewModel {
    public DecimalQuestionViewModel(DecimalQuestion question, Function<NumericExpression, NumericExpressionViewModel> factory) {
        super(question, factory);
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
