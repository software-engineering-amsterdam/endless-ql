package qls.model.style;

import qls.visitor.IQLSVisitor;

public class FontSizeAttribute extends StyleAttribute {
    private final int fontSize;

    public FontSizeAttribute(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
