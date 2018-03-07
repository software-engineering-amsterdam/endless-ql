package qlviz.gui.viewModel;

import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.booleanExpressions.BooleanExpression;

import java.util.List;

public interface ConditionalBlockViewModel {

    BooleanExpressionViewModel getCondition();

    List<QuestionBlockViewModel> getQuestionBlocks();

}
