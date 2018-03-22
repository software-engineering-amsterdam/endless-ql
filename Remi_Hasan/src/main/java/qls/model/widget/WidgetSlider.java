package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetSlider extends Widget {

    public final double min;
    public final double max;

    public WidgetSlider(Token start, double min, double max) {
        super(start, WidgetType.SLIDER);
        this.min = min;
        this.max = max;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
