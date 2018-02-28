package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;
import qlviz.gui.viewModel.question.BaseQuestionViewModel;
import qlviz.gui.viewModel.question.BooleanQuestionViewModel;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;
import qlviz.model.question.BooleanQuestionReference;

import java.math.BigDecimal;

public class BooleanQuestionReferenceViewModel implements BooleanExpressionViewModel {

    private final String name;
    private BooleanProperty value;

    public BooleanQuestionReferenceViewModel(BooleanQuestionReference booleanQuestionReference) {
        this.name = booleanQuestionReference.getQuestionName();
        this.value = new SimpleBooleanProperty();
    }

    public void accept(BooleanExpressionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public BooleanProperty valueProperty()
    {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setQuestion(BooleanQuestionViewModel viewModel) {
        this.value.bindBidirectional(viewModel.valueProperty());
    }

    public String getQuestionName() {
        return this.name;
    }
}
