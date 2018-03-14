package gui.widgets;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.List;

public class CheckboxWidget extends Widget {
    public CheckboxWidget(String name){
        super(name);
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
