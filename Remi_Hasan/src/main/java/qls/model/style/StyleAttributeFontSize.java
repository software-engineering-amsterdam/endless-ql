package qls.model.style;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeFontSize extends StyleAttribute {
    public final int fontSize;

    public StyleAttributeFontSize(Token start, int fontSize) {
        super(start);
        this.fontSize = fontSize;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
