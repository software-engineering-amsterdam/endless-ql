package gui.widgets.slider;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import ql.evaluation.SymbolTable;
import qls.model.StyleSheet;

public abstract class SliderWidget extends HBox implements GUIWidget {
    private final String identifier;
    private final boolean computed;
    protected Slider slider;
    protected Label valueLabel;

    public SliderWidget(String identifier, boolean computed, double min, double max) {
        this.identifier = identifier;
        this.computed = computed;
        this.addSlider(min, max);
        this.addValueLabel();
    }

    private void addSlider(double min, double max) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        this.slider = slider;
        this.getChildren().add(slider);
    }

    private void addValueLabel() {
        // Show label next to slider with the current value
        Label valueLabel = new Label("0.0");
        valueLabel.setPadding(new Insets(0, 0, 0, 5));
        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> valueLabel.setText(newVal.toString()));
        this.valueLabel = valueLabel;
        this.getChildren().add(valueLabel);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if (!computed)
            slider.valueProperty().addListener(invalidationListener);
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void update(SymbolTable symbolTable) {
        if (computed) setValue(symbolTable.getValue(this.identifier));
        else symbolTable.setExpression(identifier, this.getExpressionValue());
    }

    @Override
    public void update(StyleSheet styleSheet) {

    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
