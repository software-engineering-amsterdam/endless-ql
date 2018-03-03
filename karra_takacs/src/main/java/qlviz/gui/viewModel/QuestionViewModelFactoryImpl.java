package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.*;
import qlviz.model.question.*;

public class QuestionViewModelFactoryImpl implements QuestionViewModelFactory, QuestionVisitor<QuestionViewModel> {
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
        return new DecimalQuestionViewModel(decimalQuestion);
    }

    @Override
    public QuestionViewModel visit(IntegerQuestion integerQuestion) {
        return new IntegerQuestionViewModel(integerQuestion);
    }

    @Override
    public QuestionViewModel visit(MoneyQuestion moneyQuestion) {
        return new MoneyQuestionViewModel(moneyQuestion);
    }

    @Override
    public QuestionViewModel visit(StringQuestion stringQuestion) {
        return new StringQuestionViewModel(stringQuestion);
    }
}
