package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.property.Property;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;

import java.math.BigDecimal;

public interface NumericExpressionViewModel {
    Property<BigDecimal> valueProperty();
    void accept(NumericExpressionViewModelVisitor numericExpressionViewModelVisitor);
}
