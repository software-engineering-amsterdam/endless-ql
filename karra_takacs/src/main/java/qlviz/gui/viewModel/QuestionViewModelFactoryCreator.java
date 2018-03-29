package qlviz.gui.viewModel;

import qlviz.gui.viewModel.question.QuestionViewModelFactory;
import qlviz.interpreter.ConditionCollector;

public interface QuestionViewModelFactoryCreator {
    QuestionViewModelFactory create(ConditionCollector conditionCollector);
}
