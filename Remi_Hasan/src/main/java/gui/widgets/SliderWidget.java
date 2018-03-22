package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.Slider;

public abstract class SliderWidget extends Slider implements GUIWidget {

    public SliderWidget(double min, double max) {
        this.setMin(min);
        this.setMax(max);
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.setMinorTickCount(5);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.valueProperty().addListener(invalidationListener);
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
}
