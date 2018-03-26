package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

import java.util.Map;

public abstract class BaseRenderable {

    static final double TEXT_WIDTH = 100.0;
    private static final int LABEL_WIDTH = 350;
    private static final int LABEL_HEIGHT = 40;

    public abstract Node render(Map<String, VBox> containers);

    Node drawComponent(String label, Node widget) {
        return drawComponent(label, widget, "");
    }

    private Node drawComponent(String label, Node widget, String extraLabel) {
        GridPane wrapper = new GridPane();
        wrapper.getColumnConstraints().add(new ColumnConstraints(BaseRenderable.LABEL_WIDTH));
        wrapper.getRowConstraints().add(new RowConstraints(BaseRenderable.LABEL_HEIGHT));
        wrapper.addRow(0, this.createLabel(label), this.createLabel(extraLabel), widget);
        return wrapper;
    }

    private Label createLabel(String string) {
        Label label = new Label(string.replace("\"", ""));
        label.setWrapText(true);
        return label;
    }

    public boolean updateValue(BooleanValue booleanValue) {
        return false;
    }

    public boolean updateValue(DateValue booleanValue) {
        return false;
    }

    public boolean updateValue(DecimalValue booleanValue) {
        return false;
    }

    public boolean updateValue(IntValue booleanValue) {
        return false;
    }

    public boolean updateValue(MoneyValue booleanValue) {
        return false;
    }

    public boolean updateValue(StringValue booleanValue) {
        return false;
    }
}
