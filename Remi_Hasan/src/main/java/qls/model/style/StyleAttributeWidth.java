package qls.model.style;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeWidth extends StyleAttribute {
    public final int width;

    public StyleAttributeWidth(Token start, int width) {
        super(start);
        this.width = width;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getWidth() {
        return width;
    }
}
