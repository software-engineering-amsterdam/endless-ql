package qls.model.style;

import qls.visitor.IQLSVisitor;

public class ColorAttribute extends StyleAttribute {
    private final String color;

    public ColorAttribute(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
