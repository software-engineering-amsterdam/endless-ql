package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class BooleanLiteralViewModel implements BooleanExpressionViewModel {

    private final BooleanProperty value;

    public BooleanLiteralViewModel(boolean value) {
        this.value = new SimpleBooleanProperty(value);
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

