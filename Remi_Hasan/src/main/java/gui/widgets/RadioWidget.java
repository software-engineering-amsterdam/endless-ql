package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public class RadioWidget extends Widget<HBox> {

    private final List<String> options;

    public RadioWidget(ChangeListener listener, String name, List<String> options){
        super(name);
        this.options = options;

        HBox pane = new HBox();
        ToggleGroup group = new ToggleGroup();
        for(String option : options){
            RadioButton optionButton = new RadioButton(option);
            pane.getChildren().add(optionButton);
            optionButton.setToggleGroup(group);
        }

        this.control = pane;
        group.selectedToggleProperty().addListener(listener);
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public String getValue() {
//        return control.getch.getSelectedToggle().getUserData().toString();
        return "";
    }
}
