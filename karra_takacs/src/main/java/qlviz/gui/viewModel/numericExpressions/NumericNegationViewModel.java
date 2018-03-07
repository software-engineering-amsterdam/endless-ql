package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;
import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericNegationViewModel implements NumericExpressionViewModel {

    private final Property<BigDecimal> value;
    private final NumericExpressionViewModel innerExpression;

    public NumericNegationViewModel(NumericExpressionViewModel innerExpression) {
        this.value = new SimpleObjectProperty<>();
        this.value.bind(new ObjectBinding<BigDecimal>() {
            {
                bind(innerExpression.valueProperty());
            }
            @Override
            protected BigDecimal computeValue() {
                return innerExpression.valueProperty().getValue().negate();
            }
        });
        this.innerExpression = innerExpression;
    }

    @Override
    public Property<BigDecimal> valueProperty() {
        return this.value;
    }

    @Override
    public void accept(NumericExpressionViewModelVisitor numericExpressionViewModelVisitor) {
        numericExpressionViewModelVisitor.visit(this);
    }

    public NumericExpressionViewModel getInnerExpression() {
        return innerExpression;
    }
}
