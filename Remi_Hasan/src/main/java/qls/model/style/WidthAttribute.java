package qls.model.style;

import qls.visitor.IQLSVisitor;

public class WidthAttribute extends StyleAttribute {
    private final int width;

    public WidthAttribute(int width) {
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
