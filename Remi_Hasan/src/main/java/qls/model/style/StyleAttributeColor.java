package qls.model.style;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeColor extends StyleAttribute {
    private final String color;

    public StyleAttributeColor(Token start, String color) {
        super(start);
        this.color = color;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
