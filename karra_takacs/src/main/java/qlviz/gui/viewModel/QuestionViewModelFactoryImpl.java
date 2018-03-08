package qlviz.gui.viewModel;

import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.gui.viewModel.question.*;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.*;

import java.util.function.Function;

public class QuestionViewModelFactoryImpl implements QuestionViewModelFactory, QuestionVisitor<QuestionViewModel> {
    private Function<NumericExpression, NumericExpressionViewModel> factory;

    public QuestionViewModelFactoryImpl(Function<NumericExpression, NumericExpressionViewModel> numericExpressionViewModelFactory) {
        this.factory = numericExpressionViewModelFactory;
    }

    @Override
    public QuestionViewModel create(Question model) {
        return model.accept(this);
    }

    @Override
    public QuestionViewModel visit(BooleanQuestion booleanQuestion) {
        return new BooleanQuestionViewModel(booleanQuestion);
    }

    @Override
    public QuestionViewModel visit(DateQuestion dateQuestion) {
        return new DateQuestionViewModel(dateQuestion);
    }

    @Override
    public QuestionViewModel visit(DecimalQuestion decimalQuestion) {
        return new DecimalQuestionViewModel(decimalQuestion, factory);
    }

    @Override
    public QuestionViewModel visit(IntegerQuestion integerQuestion) {
        return new IntegerQuestionViewModel(integerQuestion, factory);
    }

    @Override
    public QuestionViewModel visit(MoneyQuestion moneyQuestion) {
        return new MoneyQuestionViewModel(moneyQuestion, factory);
    }

    @Override
    public QuestionViewModel visit(StringQuestion stringQuestion) {
        return new StringQuestionViewModel(stringQuestion);
    }
}
