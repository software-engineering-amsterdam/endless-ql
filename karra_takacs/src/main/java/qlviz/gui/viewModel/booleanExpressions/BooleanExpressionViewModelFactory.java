package qlviz.gui.viewModel.booleanExpressions;

import qlviz.model.booleanExpressions.BooleanExpression;

public interface BooleanExpressionViewModelFactory {
    BooleanExpressionViewModel create(BooleanExpression booleanExpression);
}
