package qlviz.gui.viewModel;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactory;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactory;
import qlviz.gui.viewModel.question.*;
import qlviz.interpreter.ConditionCollector;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionViewModelFactoryImpl implements QuestionViewModelFactory, QuestionVisitor<QuestionViewModel> {
    private NumericExpressionViewModelFactory numericExpressionViewModelFactory;
    private final BooleanExpressionViewModelFactory booleanExpressionViewModelFactory;
    private final ConditionCollector conditionCollector;

    @Inject
    public QuestionViewModelFactoryImpl(
            NumericExpressionViewModelFactory numericExpressionViewModelFactory,
            BooleanExpressionViewModelFactory booleanExpressionViewModelFactory,
            @Assisted ConditionCollector conditionCollector) {
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
                conditionCollector.getConditions(booleanQuestion)
                                    .stream()
                                    .map(booleanExpressionViewModelFactory::create)
                                    .collect(Collectors.toList());
        return new BooleanQuestionViewModel(booleanQuestion, conditions);
    }

    @Override
    public QuestionViewModel visit(DateQuestion dateQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.getConditions(dateQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory::create)
                        .collect(Collectors.toList());
        return new DateQuestionViewModel(dateQuestion, conditions);
    }

    @Override
    public QuestionViewModel visit(DecimalQuestion decimalQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.getConditions(decimalQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory::create)
                        .collect(Collectors.toList());
        return new DecimalQuestionViewModel(decimalQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(IntegerQuestion integerQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.getConditions(integerQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory::create)
                        .collect(Collectors.toList());
        return new IntegerQuestionViewModel(integerQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(MoneyQuestion moneyQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.getConditions(moneyQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory::create)
                        .collect(Collectors.toList());
        return new MoneyQuestionViewModel(moneyQuestion, numericExpressionViewModelFactory, conditions);
    }

    @Override
    public QuestionViewModel visit(StringQuestion stringQuestion) {
        List<BooleanExpressionViewModel> conditions =
                conditionCollector.getConditions(stringQuestion)
                        .stream()
                        .map(booleanExpressionViewModelFactory::create)
                        .collect(Collectors.toList());
        return new StringQuestionViewModel(stringQuestion, conditions);
    }
}
