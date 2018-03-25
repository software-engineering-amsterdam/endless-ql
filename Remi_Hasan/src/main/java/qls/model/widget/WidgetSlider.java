package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetSlider extends Widget {

    private final double minValue;
    private final double maxValue;

    public WidgetSlider(Token start, double min, double max) {
        super(start, WidgetType.SLIDER);
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
