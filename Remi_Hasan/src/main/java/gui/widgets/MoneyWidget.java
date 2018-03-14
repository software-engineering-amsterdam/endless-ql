package gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MoneyWidget extends Widget {

    public MoneyWidget(String name) {
        super(name);
    }

    @Override
    public Pane get() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        TextField textField = new TextField();
        TextFormatter formatter = createTextFormatter("-?\\d*(\\.\\d{0,2})?");
        textField.setTextFormatter(formatter);
        pane.getChildren().add(new Label(name));
        pane.getChildren().add(new Label("$"));
        pane.getChildren().add(textField);

        return pane;
    }
}
