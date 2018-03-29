package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetSlider extends Widget {

    private final double minValue;
    private final double maxValue;

    public WidgetSlider(double min, double max) {
        super(WidgetType.SLIDER);
        this.minValue = min;
        this.maxValue = max;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
