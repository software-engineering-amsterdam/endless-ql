package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CheckboxWidget extends Widget {
    public CheckboxWidget(String name, ChangeListener<? super String> listener){
        super(name, listener);
    }


    @Override
    public Pane getUI() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        pane.getChildren().add(new Label(name));
        CheckBox checkBox = new CheckBox();
        pane.getChildren().add(checkBox);

        return pane;
    }
}
