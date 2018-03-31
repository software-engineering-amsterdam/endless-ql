package qlviz.gui.viewModel.question;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactory;
import qlviz.model.expressions.NumericExpressionGetter;
import qlviz.model.question.NumericQuestion;

import java.math.BigDecimal;
import java.util.List;

public abstract class NumericQuestionViewModel extends BaseQuestionViewModel {

    private final NumericQuestion question;
    private final Property<BigDecimal> value;
    private final NumericExpressionViewModel expression;

    protected NumericQuestionViewModel(
            NumericQuestion question,
            NumericExpressionViewModelFactory viewModelFactory,
            List<BooleanExpressionViewModel> conditions) {
        super(question, conditions);
        this.question = question;
        this.value = new SimpleObjectProperty<>(BigDecimal.ZERO);
        if (question.getValueExpression() != null) {
            this.expression = viewModelFactory.create(
                    question.getValueExpression().accept(new NumericExpressionGetter()));
            this.value.bind(this.expression.valueProperty());
        }
        else
        {
            this.expression = null;
        }
    }

    public Property<BigDecimal> valueProperty() {
        return this.value;
    }

    public NumericExpressionViewModel getValueExpression() {
        return this.expression;
    }
}
