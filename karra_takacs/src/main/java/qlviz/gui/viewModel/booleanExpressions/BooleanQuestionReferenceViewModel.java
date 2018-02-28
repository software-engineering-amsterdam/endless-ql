package qlviz.gui.viewModel.booleanExpressions;

import javafx.beans.property.BooleanProperty;

public class BooleanQuestionReferenceViewModel implements BooleanExpressionViewModel {
    @Override
    public void accept(BooleanExpressionViewModelVisitor visitor) {

    }

    @Override
    public BooleanProperty valueProperty() {
        return null;
    }
}
