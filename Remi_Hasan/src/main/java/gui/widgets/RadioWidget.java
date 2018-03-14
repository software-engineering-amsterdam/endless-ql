package gui.widgets;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public class RadioWidget extends Widget {

    private final List<String> options;

    public RadioWidget(String name, List<String> options){
        super(name);
        this.options = options;
    }


    @Override
    public Pane get() {
        HBox pane = new HBox();
        pane.setSpacing(20);

        ToggleGroup group = new ToggleGroup();
        for(String option : options){
            RadioButton optionButton = new RadioButton(option);
            pane.getChildren().add(optionButton);
            optionButton.setToggleGroup(group);
        }

        return pane;
    }
}
