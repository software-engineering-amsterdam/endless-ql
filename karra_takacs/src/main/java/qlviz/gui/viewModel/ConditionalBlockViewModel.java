package qlviz.gui.viewModel;

import qlviz.model.BooleanExpression;
import qlviz.model.QuestionBlock;

import java.util.List;

public interface ConditionalBlockViewModel {

    BooleanExpression getCondition();

    List<QuestionBlockViewModel> getQuestionBlocks();
}
