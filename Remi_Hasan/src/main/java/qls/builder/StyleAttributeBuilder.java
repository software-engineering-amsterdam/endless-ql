package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.style.*;

public class StyleAttributeBuilder extends QLSBaseVisitor<StyleAttribute> {

    @Override
    public StyleAttribute visitWidgetWidth(QLSParser.WidgetWidthContext ctx) {
        int width = Integer.parseInt(ctx.value.getText());
        WidthAttribute widthAttribute = new WidthAttribute(width);
        widthAttribute.setToken(ctx.getStart());
        return widthAttribute;
    }

    @Override
    public StyleAttribute visitWidgetFont(QLSParser.WidgetFontContext ctx) {
        String fontFamily = ctx.value.getText();
        // Strip quotes
        fontFamily = fontFamily.substring(1, fontFamily.length() - 1);
        FontStyleAttribute fontStyleAttribute = new FontStyleAttribute(fontFamily);
        fontStyleAttribute.setToken(ctx.getStart());
        return fontStyleAttribute;
    }

    @Override
    public StyleAttribute visitWidgetFontSize(QLSParser.WidgetFontSizeContext ctx) {
        int fontSize = Integer.parseInt(ctx.value.getText());
        FontSizeAttribute fontSizeAttribute = new FontSizeAttribute(fontSize);
        fontSizeAttribute.setToken(ctx.getStart());
        return fontSizeAttribute;
    }

    @Override
    public StyleAttribute visitWidgetColor(QLSParser.WidgetColorContext ctx) {
        String hexColor = ctx.value.getText();
        ColorAttribute colorAttribute = new ColorAttribute(hexColor);
        colorAttribute.setToken(ctx.getStart());
        return colorAttribute;
    }
}
