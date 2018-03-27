package qlviz.gui.viewModel.booleanExpressions;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;

public interface BooleanExpressionViewModelFactory {
    BooleanExpressionViewModel create(BooleanExpression booleanExpression);
}
