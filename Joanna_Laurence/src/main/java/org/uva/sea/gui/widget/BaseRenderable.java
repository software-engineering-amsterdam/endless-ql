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

    final Node drawComponent(final String label, final Node widget) {
        return this.drawComponent(label, widget, "");
    }

    private Node drawComponent(final String label, final Node widget, final String extraLabel) {
        final GridPane wrapper = new GridPane();
        wrapper.getColumnConstraints().add(new ColumnConstraints(BaseRenderable.LABEL_WIDTH));
        wrapper.getRowConstraints().add(new RowConstraints(BaseRenderable.LABEL_HEIGHT));
        wrapper.addRow(0, this.createLabel(label), this.createLabel(extraLabel), widget);
        return wrapper;
    }

    private Label createLabel(final String labelName) {
        final Label label = new Label(labelName.replace("\"", ""));
        label.setWrapText(true);
        return label;
    }

    public boolean updateValue(final BooleanValue booleanValue) {
        return false;
    }

    public boolean updateValue(final DateValue booleanValue) {
        return false;
    }

    public boolean updateValue(final DecimalValue booleanValue) {
        return false;
    }

    public boolean updateValue(final IntValue booleanValue) {
        return false;
    }

    public boolean updateValue(final MoneyValue booleanValue) {
        return false;
    }

    public boolean updateValue(final StringValue booleanValue) {
        return false;
    }
}
