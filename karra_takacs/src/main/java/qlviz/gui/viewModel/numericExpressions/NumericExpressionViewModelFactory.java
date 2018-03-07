package qlviz.gui.viewModel.numericExpressions;

import qlviz.model.numericExpressions.NumericExpression;

public interface NumericExpressionViewModelFactory {
    NumericExpressionViewModel create(NumericExpression expression);
}
