package qlviz.gui.viewModel.numericExpressions;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.gui.viewModel.linker.NumericExpressionViewModelVisitor;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.model.numericExpressions.BinaryNumericOperator;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class BinaryNumericOperationViewModel implements NumericExpressionViewModel {


    private final NumericExpressionViewModel leftSide;
    private final NumericExpressionViewModel rightSide;
    private final BinaryNumericOperator operator;


    private static ObjectBinding<BigDecimal> CreateBinding(Property<BigDecimal> left,
                                                        Property<BigDecimal> right,
                                                        BiFunction<BigDecimal, BigDecimal, BigDecimal> operator)
    {
        return new ObjectBinding<BigDecimal>() {
            {
                bind(left, right);
            }
            @Override
            protected BigDecimal computeValue() {
                return operator.apply(left.getValue(), right.getValue());
            }
        };
    }

    public BinaryNumericOperationViewModel(NumericExpressionViewModel leftSide, NumericExpressionViewModel rightSide, BinaryNumericOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
        Property<BigDecimal> value = new SimpleObjectProperty<>();
        switch (this.operator) {
            case Add:
                value.bind(CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), BigDecimal::add));
            case Subtract:
                value.bind(CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), BigDecimal::subtract));
            case Multiply:
                value.bind(CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), BigDecimal::multiply));
            case Divide:
                value.bind(CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), BigDecimal::divide));
        }

    }

    @Override
    public Property<BigDecimal> valueProperty() {
        return this.valueProperty();
    }

    @Override
    public void accept(NumericExpressionViewModelVisitor numericExpressionViewModelVisitor) {
        numericExpressionViewModelVisitor.visit(this);
    }

    public NumericExpressionViewModel getLeftSide() {
        return leftSide;
    }

    public NumericExpressionViewModel getRightSide() {
        return rightSide;
    }
}
