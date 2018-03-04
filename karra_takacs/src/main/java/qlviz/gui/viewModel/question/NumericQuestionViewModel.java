package qlviz.gui.viewModel.question;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.NumericQuestion;

import java.math.BigDecimal;
import java.util.function.Function;

public abstract class NumericQuestionViewModel extends BaseQuestionViewModel {

    private final NumericQuestion question;
    private final Property<BigDecimal> value;
    private final NumericExpressionViewModel expression;

    protected NumericQuestionViewModel(NumericQuestion question, Function<NumericExpression, NumericExpressionViewModel> viewModelFactory) {
        super(question);
        this.question = question;
        this.value = new SimpleObjectProperty<>(BigDecimal.ZERO);
        if (question.getValueExpression() != null) {
            this.expression = viewModelFactory.apply(question.getValueExpression());
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
