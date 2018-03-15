package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import qls.model.StyleSheet;

import java.util.List;

public class DropdownWidget extends Widget {

    private final List<String> options;

    public DropdownWidget(StyleSheet styleSheet, String name, ChangeListener<? super String> listener, List<String> options){
        super(name, listener);
        this.options = options;
    }


    @Override
    public Pane getUI() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        ComboBox<String> comboBox = new ComboBox<>();
        for(String option : options){
            comboBox.getItems().add(option);
        }

        pane.getChildren().add(new Label(name));
        pane.getChildren().add(comboBox);

        comboBox.valueProperty().addListener(listener);

        return pane;
    }
}
