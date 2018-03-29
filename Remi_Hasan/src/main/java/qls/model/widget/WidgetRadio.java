package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class WidgetRadio extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public WidgetRadio(Token token, String trueLabel, String falseLabel) {
        super(WidgetType.RADIO);
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
