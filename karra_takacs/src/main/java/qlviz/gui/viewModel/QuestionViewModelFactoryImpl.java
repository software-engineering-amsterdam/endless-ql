package qlviz.gui.viewModel;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.gui.viewModel.question.*;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionViewModelFactoryImpl implements QuestionViewModelFactory, QuestionVisitor<QuestionViewModel> {
    private Function<NumericExpression, NumericExpressionViewModel> numericExpressionViewModelFactory;
    private final Function<BooleanExpression, BooleanExpressionViewModel> booleanExpressionViewModelFactory;
    private final Function<Question, List<BooleanExpression>> conditionCollector;

    public QuestionViewModelFactoryImpl(
            Function<NumericExpression, NumericExpressionViewModel> numericExpressionViewModelFactory,
            Function<BooleanExpression, BooleanExpressionViewModel> booleanExpressionViewModelFactory,
            Function<Question, List<BooleanExpression>> conditionCollector) {
        this.numericExpressionViewModelFactory = numericExpressionViewModelFactory;
        this.booleanExpressionViewModelFactory = booleanExpressionViewModelFactory;
        this.conditionCollector = conditionCollector;
    }

    @Override
    public QuestionViewModel create(Question model) {
        return model.accept(this);
    }

    @Override
    public QuestionViewModel visit(BooleanQuestion booleanQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(booleanQuestion)
                                    .stream()
                                    .map(booleanExpressionViewModelFactory)
                                    .collect(Collectors.toList());
        return new BooleanQuestionViewModel(booleanQuestion, conditions);
    }

    @Override
    public QuestionViewModel visit(DateQuestion dateQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(dateQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory)
                        .collect(Collectors.toList());
        return new DateQuestionViewModel(dateQuestion, conditions);
    }

    @Override
    public QuestionViewModel visit(DecimalQuestion decimalQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(decimalQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory)
                        .collect(Collectors.toList());
        return new DecimalQuestionViewModel(decimalQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(IntegerQuestion integerQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(integerQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory)
                        .collect(Collectors.toList());
        return new IntegerQuestionViewModel(integerQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(MoneyQuestion moneyQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(moneyQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory)
                        .collect(Collectors.toList());
        return new MoneyQuestionViewModel(moneyQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(StringQuestion stringQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.apply(stringQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory)
                        .collect(Collectors.toList());
        return new StringQuestionViewModel(stringQuestion, conditions);
    }
}
