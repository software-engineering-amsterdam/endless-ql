package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;
import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericLiteralViewModel implements NumericExpressionViewModel {

    private final Property<BigDecimal> value;

    public NumericLiteralViewModel(BigDecimal value) {
        this.value = new SimpleObjectProperty<>(value);
    }

    @Override
    public Property<BigDecimal> valueProperty() {
        return this.value;
    }

    @Override
    public void accept(NumericExpressionViewModelVisitor numericExpressionViewModelVisitor) {
        numericExpressionViewModelVisitor.visit(this);
    }
}
