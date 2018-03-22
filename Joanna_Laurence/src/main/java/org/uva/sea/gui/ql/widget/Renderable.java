package org.uva.sea.gui.ql.widget;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

import java.util.Map;

public abstract class Renderable {

    protected static final double TEXT_WIDTH = 100.0;
    private static final int COLUMN = 350;
    private static final int ROW = 40;

    private static final int MESSAGE_ROW = 600;
    private static final int MESSAGE_COLUMN = 40;

    public abstract void render(Map<String, VBox> containers);

    protected Node createRow(String label, Control widget) {
        GridPane wrapper = new GridPane();
        wrapper.getColumnConstraints().add(new ColumnConstraints(Renderable.COLUMN));
        wrapper.getRowConstraints().add(new RowConstraints(Renderable.ROW));
        wrapper.add(this.createLabel(label), 0, 0);
        wrapper.add(widget, 1, 0);
        return wrapper;
    }

    private Label createLabel(String string) {
        Label label = new Label(string.replace("\"", ""));
        label.setWrapText(true);
        return label;
    }

    protected Node createTextRow(String message) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(Renderable.MESSAGE_ROW));
        wrapper.getRowConstraints().add(new RowConstraints(Renderable.MESSAGE_COLUMN));

        Label label = new Label(message);
        label.setWrapText(true);
        wrapper.add(label, 0, 0);

        return wrapper;
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
