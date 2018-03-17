package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import java.util.List;

public class WidgetRadio extends Widget {

    private final List<String> options;

    public WidgetRadio(Token token, List<String> options) {
        super(token, WidgetType.RADIO);
        this.options = options;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
