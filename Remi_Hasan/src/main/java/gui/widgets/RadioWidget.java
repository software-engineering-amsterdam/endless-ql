package gui.widgets;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.model.expression.Expression;

public class RadioWidget extends HBox implements WidgetInterface{

    private final String name;

    public RadioWidget(String name){
        this.name = name;
        this.managedProperty().bind(this.visibleProperty());

        ToggleGroup group = new ToggleGroup();

        RadioButton falseButton = new RadioButton("false");
        RadioButton trueButton = new RadioButton("true");

        trueButton.setToggleGroup(group);
        falseButton.setToggleGroup(group);

        this.getChildren().add(falseButton);
        this.getChildren().add(trueButton);
    }

    @Override
    public void setExpression(String value) {

    }

    @Override
    public Expression getExpression() {
        return null;
    }
}
