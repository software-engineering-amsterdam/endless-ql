package gui.widgets;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public class DropdownWidget extends Widget {

    private final List<String> options;

    public DropdownWidget(String name, List<String> options){
        super(name);
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

        return pane;
    }
}
