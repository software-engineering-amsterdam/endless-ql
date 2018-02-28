package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModel;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.model.booleanExpressions.NumericComparisonOperator;
import qlviz.model.numericExpressions.NumericExpression;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class NumericComparisonViewModel implements BooleanExpressionViewModel {

    private final NumericExpressionViewModel leftSide;
    private final NumericExpressionViewModel rightSide;
    private final BooleanProperty value;

    private static ObjectBinding<Boolean> CreateBinding(Property<BigDecimal> left,
                                                        Property<BigDecimal> right,
                                                        BiFunction<BigDecimal, BigDecimal, Boolean> operator)
    {
        return new ObjectBinding<Boolean>() {
            {
                bind(left, right);
            }
            @Override
            protected Boolean computeValue() {
                return operator.apply(left.getValue(), right.getValue());
            }
        };
    }

    public NumericComparisonViewModel(NumericExpressionViewModel leftSide, NumericExpressionViewModel rightSide, NumericComparisonOperator opeartor) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        BooleanProperty property = new SimpleBooleanProperty();
        switch (opeartor) {
            case Equal:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), BigDecimal::equals));
            case NotEqual:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), (a,b) -> !a.equals(b)));
            case Smaller:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), (a,b) -> a.compareTo(b) < 0));
            case SmallerOrEqual:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(),
                                (a,b) -> a.compareTo(b) < 0 || a.equals(b)));
            case Greater:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(), (a,b) -> a.compareTo(b) > 0));
            case GreaterOrEqual:
                property.bind(
                        CreateBinding(this.leftSide.valueProperty(), this.rightSide.valueProperty(),
                                (a,b) -> a.compareTo(b) > 0 || a.equals(b)));
        }
        this.value = property;
    }

    @Override
    public void accept(BooleanExpressionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BooleanProperty valueProperty() {
        return value;
    }
}
