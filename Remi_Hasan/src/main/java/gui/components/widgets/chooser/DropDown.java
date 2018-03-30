package gui.components.widgets.chooser;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.BooleanConstant;

public class DropDown extends ComboBox<String> implements GUIWidget {

    private String color;
    private String font;
    private int fontSize;

    public DropDown(String falseLabel, String trueLabel) {
        this.setItems(FXCollections.observableArrayList(falseLabel, trueLabel));

        // Default to false
        this.getSelectionModel().select(0);
    }

    // Use cell factory to style dropdown options
    private void setCellFactory() {
        this.setCellFactory(param -> new ComboBoxListCell<>() {{
            setTextFill(Color.valueOf(color));
            setFont(Font.font(font));
            setFont(Font.font(fontSize));
        }});
    }

    @Override
    public Expression getExpressionValue() {
        // Item at index 1 is always the true option, so check if that one is selected
        boolean isTrue = this.getSelectionModel().getSelectedIndex() == 1;
        return new BooleanConstant(isTrue);
    }

    @Override
    public void setValue(Value value) {
        // Index 1 is true, index 0 is false
        this.getSelectionModel().select(value.getBooleanValue() ? 1 : 0);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.valueProperty().addListener(invalidationListener);
    }

    @Override
    public void setColor(String color) {
        this.color = color;
        setCellFactory();
    }

    @Override
    public void setFont(String font) {
        this.font = font;
        setCellFactory();
    }

    @Override
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        setCellFactory();
    }

    @Override
    public void setWidth(int width) {
        this.setWidth((double) width);
    }
}
