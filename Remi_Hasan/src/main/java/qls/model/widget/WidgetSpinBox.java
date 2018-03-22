package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetSpinBox extends Widget {
    public WidgetSpinBox(Token start) {
        super(start, WidgetType.SPINBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return null;
    }
}
