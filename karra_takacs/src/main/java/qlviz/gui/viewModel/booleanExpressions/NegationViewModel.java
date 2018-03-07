package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class NegationViewModel implements BooleanExpressionViewModel {

    private final BooleanProperty value;
    private final BooleanExpressionViewModel operand;

    public NegationViewModel(BooleanExpressionViewModel operand) {
        this.operand = operand;
        SimpleBooleanProperty property = new SimpleBooleanProperty();
        property.bind(operand.valueProperty().not());
        this.value = property;
    }

    public BooleanExpressionViewModel getOperand() {
        return operand;
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

