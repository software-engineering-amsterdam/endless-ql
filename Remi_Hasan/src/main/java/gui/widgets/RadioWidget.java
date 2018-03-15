package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public class RadioWidget extends Widget {

    private final List<String> options;

    public RadioWidget(String name, ChangeListener<? super String> listener, List<String> options){
        super(name, listener);
        this.options = options;
    }


    @Override
    public Pane getUI() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        pane.getChildren().add(new Label(name));

        ToggleGroup group = new ToggleGroup();
        for(String option : options){
            RadioButton optionButton = new RadioButton(option);
            pane.getChildren().add(optionButton);
            optionButton.setToggleGroup(group);
        }

        return pane;
    }
}
