package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class SliderWidget extends Widget {

    private final int minValue;
    private final int maxValue;

    public SliderWidget(int min, int max) {
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
