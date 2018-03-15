package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class Widget<T extends Node> {

    public final String label;
    T control;

    public Widget(String label){
        this.label = label;
    }

    TextFormatter createTextFormatter(String pattern) {
        Pattern textPattern = Pattern.compile(pattern);
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (textPattern.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null;
            }
        };
        return new TextFormatter<>(filter);
    }

    public Pane getUI() {
        VBox pane = new VBox();
        pane.setSpacing(5);

        pane.getChildren().add(new Label(label));
        pane.getChildren().add(control);

        return pane;
    }

    public abstract void setValue(String value);

    public abstract String getValue();

    public void setDisable(boolean disable) {
        this.control.setDisable(disable);
    }

}
