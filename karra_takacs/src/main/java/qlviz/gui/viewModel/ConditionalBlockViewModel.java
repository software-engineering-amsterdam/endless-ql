package qlviz.gui.viewModel;

import javafx.beans.value.ObservableValue;
import qlviz.gui.viewModel.propertyEvents.NotifyPropertyChanged;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.booleanExpressions.BooleanExpression;

import java.util.List;

public interface ConditionalBlockViewModel extends NotifyPropertyChanged<QuestionViewModel> {

    BooleanExpression getCondition();

    List<QuestionBlockViewModel> getQuestionBlocks();

}
