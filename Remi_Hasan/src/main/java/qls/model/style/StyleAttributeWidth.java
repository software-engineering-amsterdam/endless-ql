package qls.model.style;

import qls.IQLSVisitor;

public class StyleAttributeWidth extends StyleAttribute {
    private final int width;

    public StyleAttributeWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
