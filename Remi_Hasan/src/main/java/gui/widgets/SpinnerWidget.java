package gui.widgets;

import javafx.scene.control.Spinner;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.model.Question;

public abstract class SpinnerWidget<T> extends Spinner<T> implements WidgetInterface {

    final Question question;

    SpinnerWidget(Question question) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, 0.0);
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.setEditable(true);
    }


    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.getValueFactory().valueProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }


    @Override
    public void setColor(String color) {
        this.setStyle("-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {

        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        this.setPrefWidth(width);
    }
}

