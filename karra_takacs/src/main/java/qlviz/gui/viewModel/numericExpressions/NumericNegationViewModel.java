package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericNegationViewModel implements NumericExpressionViewModel {

    private final Property<BigDecimal> value;

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
    }

    @Override
    public Property<BigDecimal> valueProperty() {
        return this.value;
    }
}
