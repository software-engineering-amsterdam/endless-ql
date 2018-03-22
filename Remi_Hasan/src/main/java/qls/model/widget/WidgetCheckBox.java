package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetCheckBox extends Widget {

    public WidgetCheckBox(Token start) {
        super(start, WidgetType.CHECKBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
