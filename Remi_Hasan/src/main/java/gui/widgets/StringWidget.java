package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StringWidget extends Widget {

    public StringWidget(String name, ChangeListener<? super String> listener) {
        super(name, listener);
    }

    @Override
    public Pane getUI() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        TextField textField = new TextField();
        pane.getChildren().add(new Label(name));
        pane.getChildren().add(textField);

        return pane;
    }
}
