package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.style.*;

public class VisitorStyleAttribute extends QLSBaseVisitor<StyleAttribute> {

    @Override
    public StyleAttribute visitWidgetWidth(QLSParser.WidgetWidthContext ctx) {
        int width = Integer.parseInt(ctx.value.getText());
        StyleAttributeWidth styleAttributeWidth = new StyleAttributeWidth(width);
        styleAttributeWidth.setToken(ctx.getStart());
        return styleAttributeWidth;
    }

    @Override
    public StyleAttribute visitWidgetFont(QLSParser.WidgetFontContext ctx) {
        String fontFamily = ctx.value.getText();
        // Strip quotes
        fontFamily = fontFamily.substring(1, fontFamily.length() - 1);
        StyleAttributeFont styleAttributeFont = new StyleAttributeFont(fontFamily);
        styleAttributeFont.setToken(ctx.getStart());
        return styleAttributeFont;
    }

    @Override
    public StyleAttribute visitWidgetFontSize(QLSParser.WidgetFontSizeContext ctx) {
        int fontSize = Integer.parseInt(ctx.value.getText());
        StyleAttributeFontSize styleAttributeFontSize = new StyleAttributeFontSize(fontSize);
        styleAttributeFontSize.setToken(ctx.getStart());
        return styleAttributeFontSize;
    }

    @Override
    public StyleAttribute visitWidgetColor(QLSParser.WidgetColorContext ctx) {
        String hexColor = ctx.value.getText();
        StyleAttributeColor styleAttributeColor = new StyleAttributeColor(hexColor);
        styleAttributeColor.setToken(ctx.getStart());
        return styleAttributeColor;
    }
}
