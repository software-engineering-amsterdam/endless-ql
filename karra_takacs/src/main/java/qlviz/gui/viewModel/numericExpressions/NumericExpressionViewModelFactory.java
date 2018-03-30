package qlviz.gui.viewModel.numericExpressions;

import qlviz.model.expressions.numericExpressions.NumericExpression;

public interface NumericExpressionViewModelFactory {
    NumericExpressionViewModel create(NumericExpression expression);
}
