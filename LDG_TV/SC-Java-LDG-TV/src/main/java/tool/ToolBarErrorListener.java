package tool;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ToolBarErrorListener extends BaseErrorListener {


    private final Label lblErrorField;

    public ToolBarErrorListener(Label lblErrorField) {
        this.lblErrorField = lblErrorField;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        Platform.runLater(() -> {
                    lblErrorField.setText("Syntax error at line " + line + ':' + charPositionInLine + " (" + msg + ')');
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-d HH:mm:ss");

                    lblErrorField.setTooltip(new Tooltip(sdf.format(cal.getTime())));
                }
        );
    }
}
