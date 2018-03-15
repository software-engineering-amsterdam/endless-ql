package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class Widget {

    public final String name;
    final ChangeListener<? super String> listener;

    public Widget(String name, ChangeListener<? super String> listener){
        this.name = name;
        this.listener = listener;
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

    public abstract Pane getUI();

}
