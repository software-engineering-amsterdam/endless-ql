package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class SliderWidget extends Widget {

    private final double minValue;
    private final double maxValue;

    public SliderWidget(double min, double max) {
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
