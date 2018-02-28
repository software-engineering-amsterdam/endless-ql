package qlviz.gui.viewModel;

import qlviz.model.booleanExpressions.BooleanExpression;

import java.util.List;

public interface ConditionalBlockViewModel {

    BooleanExpression getCondition();

    List<QuestionBlockViewModel> getQuestionBlocks();
}
