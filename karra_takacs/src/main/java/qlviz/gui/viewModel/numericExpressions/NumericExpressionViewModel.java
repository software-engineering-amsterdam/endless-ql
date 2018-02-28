package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.property.Property;

import java.math.BigDecimal;

public interface NumericExpressionViewModel {
    Property<BigDecimal> valueProperty();
}
