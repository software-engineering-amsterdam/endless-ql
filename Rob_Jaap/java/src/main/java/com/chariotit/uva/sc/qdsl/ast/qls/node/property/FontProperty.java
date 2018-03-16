package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class FontProperty extends Property {

    private String font;

    public FontProperty(String font, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitFontProperty(this);
    }
}
