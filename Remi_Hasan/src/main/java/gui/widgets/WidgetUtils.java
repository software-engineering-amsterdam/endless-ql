package gui.widgets;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class WidgetUtils {
    public static TextFormatter createTextFormatter(String pattern) {
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
}
