package gui.widgets;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DateWidget extends Widget {
    public DateWidget(String name) {
        super(name);
    }

    @Override
    public Pane getUI() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        DatePicker datePicker = new DatePicker();
        pane.getChildren().add(new Label(name));
        pane.getChildren().add(datePicker);

        return pane;
    }
}
