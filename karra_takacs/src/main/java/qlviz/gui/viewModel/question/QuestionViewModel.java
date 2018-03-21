package qlviz.gui.viewModel.question;


import javafx.beans.property.BooleanProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.QuestionType;

import java.util.List;

public interface QuestionViewModel {
    void accept(QuestionViewModelVisitor visitor);
    <T> T accept(TypedQuestionViewModelVisitor<T> visitor);
    String getText();
    String getName();
    QuestionType getQuestionType();
    BooleanProperty isEnabledProperty();
    List<BooleanExpressionViewModel> getEnabledConditions();
}


