package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelVisitor;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;
import qlviz.model.question.NumericQuestionReference;

import java.math.BigDecimal;

public class NumericQuestionReferenceViewModel implements NumericExpressionViewModel {

    private final String name;
    private Property<BigDecimal> value;

    public NumericQuestionReferenceViewModel(NumericQuestionReference numericQuestionReference) {
        this.name = numericQuestionReference.getQuestionName();
        this.value = new SimpleObjectProperty<>();
    }

    public void accept(NumericExpressionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public Property<BigDecimal> valueProperty()
    {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setQuestion(NumericQuestionViewModel viewModel) {
        this.value.bind(viewModel.valueProperty());
    }
}
