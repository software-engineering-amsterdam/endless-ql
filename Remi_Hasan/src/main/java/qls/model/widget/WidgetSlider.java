package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetSlider extends Widget {

    public WidgetSlider(Token start) {
        super(start, WidgetType.SLIDER);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
