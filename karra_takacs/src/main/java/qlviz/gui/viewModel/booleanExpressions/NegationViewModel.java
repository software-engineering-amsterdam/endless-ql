package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class NegationViewModel implements BooleanExpressionViewModel {

    private final BooleanProperty value;

    public NegationViewModel(BooleanExpressionViewModel operand) {
        SimpleBooleanProperty property = new SimpleBooleanProperty();
        property.bind(operand.valueProperty().not());
        this.value = property;
    }


    @Override
    public void accept(BooleanExpressionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BooleanProperty valueProperty() {
        return this.value;
    }
}

