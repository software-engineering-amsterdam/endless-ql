package qlviz.gui.viewModel.question;


import javafx.beans.property.BooleanProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;

import java.util.List;

public interface QuestionViewModel {
    void accept(QuestionViewModelVisitor visitor);
    String getText();
    BooleanProperty isEnabledProperty();
    List<BooleanExpressionViewModel> getEnabledConditions();
}


