package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetDropdown extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public WidgetDropdown(Token token, String trueLabel, String falseLabel) {
        super(token, WidgetType.DROPDOWN);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    public String getTrueLabel() {
        return trueLabel;
    }

    public String getFalseLabel() {
        return falseLabel;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
