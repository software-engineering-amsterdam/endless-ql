package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;

public interface BooleanExpressionViewModel {
    public void accept(BooleanExpressionViewModelVisitor visitor);
    public BooleanProperty valueProperty();
}

