package gui.components.widgets.slider;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Slider extends HBox implements GUIWidget {
    javafx.scene.control.Slider slider;
    Label valueLabel;

    public Slider(double min, double max) {
        this.addSlider(min, max);
        this.addValueLabel();
    }

    private void addSlider(double min, double max) {
        javafx.scene.control.Slider slider = new javafx.scene.control.Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        this.slider = slider;
        this.getChildren().add(slider);
    }

    private void addValueLabel() {
        // Show label next to slider with the current value
        Label valueLabel = new Label(String.valueOf(this.slider.getMin()));
        valueLabel.setPadding(new Insets(0, 0, 0, 5));
        this.slider.valueProperty().addListener((obs, oldVal, newVal) -> valueLabel.setText(newVal.toString()));
        this.valueLabel = valueLabel;
        this.getChildren().add(valueLabel);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.slider.valueProperty().addListener(invalidationListener);
    }

    @Override
    public void setColor(String color) {
        this.valueLabel.setTextFill(Color.web(color));
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.valueLabel.getFont();
        this.valueLabel.setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.valueLabel.getFont();
        this.valueLabel.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        // setPrefWidth does not work, so set min and max to requested width
        this.slider.setMinWidth(width);
        this.slider.setMaxWidth(width);
    }
}
