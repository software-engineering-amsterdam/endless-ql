package qls.model.style;

import qls.IQLSVisitor;

public class StyleAttributeFont extends StyleAttribute {
    private final String fontFamily;

    public StyleAttributeFont(String fontFamily) {
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
