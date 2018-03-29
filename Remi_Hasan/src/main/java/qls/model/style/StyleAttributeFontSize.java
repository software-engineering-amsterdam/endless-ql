package qls.model.style;

import qls.IQLSVisitor;

public class StyleAttributeFontSize extends StyleAttribute {
    private final int fontSize;

    public StyleAttributeFontSize(int fontSize) {
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
