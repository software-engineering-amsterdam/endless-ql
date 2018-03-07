package qlviz.gui.viewModel.question;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.Question;

import java.util.List;

public abstract class BaseQuestionViewModel implements QuestionViewModel {

    private final Question question;
    private final List<BooleanExpressionViewModel> conditions;
    private final BooleanProperty isEnabled;
    private BooleanBinding isEnabledBinding;

    protected BaseQuestionViewModel(Question question, List<BooleanExpressionViewModel> conditions) {
        this.question = question;
        this.conditions = conditions;
        this.isEnabledBinding = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                return true;
            }
        };
        for (BooleanExpressionViewModel booleanExpressionViewModel : conditions) {
            isEnabledBinding = booleanExpressionViewModel.valueProperty().and(isEnabledBinding);
        }
        this.isEnabled = new SimpleBooleanProperty(true);
        this.isEnabled.bind(isEnabledBinding);
    }

    public String getText() {
        return this.question.getText();
    }

    public String getName(){return this.question.getName();}

    public BooleanProperty isEnabledProperty() {
        return isEnabled;
    }

    public List<BooleanExpressionViewModel> getEnabledConditions() {
        return this.conditions;
    }
}
