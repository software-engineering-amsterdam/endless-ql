package gui.widgets;

import javafx.scene.control.Spinner;
import ql.analysis.SymbolTable;
import ql.model.Question;

public abstract class SpinnerWidget<T> extends Spinner<T> implements WidgetInterface {

    final Question question;

    public SpinnerWidget(Question question) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, 0.0);
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.setEditable(true);
    }


    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.getValueFactory().valueProperty().addListener(e -> {
            symbolTable.setExpression(question.name, getExpression(this, question.type));
        });
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }
}

