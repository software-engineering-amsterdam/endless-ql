package qls.model.style;

import qls.visitor.IQLSVisitor;

public class FontStyleAttribute extends StyleAttribute {
    private final String fontFamily;

    public FontStyleAttribute(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
