package qlviz.gui.viewModel.booleanExpressions;


import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.model.booleanExpressions.BinaryBooleanOperator;

public class BinaryBooleanOperationViewModel implements BooleanExpressionViewModel {


    private final BooleanExpressionViewModel leftSide;
    private final BooleanExpressionViewModel rightSide;
    private final BooleanProperty value;

    public BinaryBooleanOperationViewModel(BooleanExpressionViewModel leftSide, BooleanExpressionViewModel rightSide, BinaryBooleanOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.value = new SimpleBooleanProperty();
        switch (operator) {
            case And:
                this.value.bind(leftSide.valueProperty().and(rightSide.valueProperty()));
                break;
            case Or:
                this.value.bind(leftSide.valueProperty().or(rightSide.valueProperty()));
                break;
        }
    }

    public BooleanExpressionViewModel getLeftSide() {
        return leftSide;
    }

    public BooleanExpressionViewModel getRightSide() {
        return rightSide;
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
