package qls.model.style;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeFont extends StyleAttribute {
    public final String fontFamily;

    public StyleAttributeFont(Token start, String fontFamily) {
        super(start);
        this.fontFamily = fontFamily;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
