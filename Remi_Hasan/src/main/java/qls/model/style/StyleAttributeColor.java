package qls.model.style;

import qls.IQLSVisitor;

public class StyleAttributeColor extends StyleAttribute {
    private final String color;

    public StyleAttributeColor(String color) {
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
