package qls.visitor;

import qls.model.style.*;
import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;

public class VisitorStyleAttribute extends QLSBaseVisitor<StyleAttribute> {

    @Override
    public StyleAttribute visitWidgetWidth(QLSParser.WidgetWidthContext ctx) {
        int width = Integer.parseInt(ctx.value.getText());
        return new StyleAttributeWidth(ctx.getStart(), width);
    }

    @Override
    public StyleAttribute visitWidgetFont(QLSParser.WidgetFontContext ctx) {
        String fontFamily = ctx.value.getText();
        // Strip quotes
        fontFamily = fontFamily.substring(1, fontFamily.length() - 1);
        return new StyleAttributeFont(ctx.getStart(), fontFamily);
    }

    @Override
    public StyleAttribute visitWidgetFontSize(QLSParser.WidgetFontSizeContext ctx) {
        int fontSize = Integer.parseInt(ctx.value.getText());
        return new StyleAttributeFontSize(ctx.getStart(), fontSize);
    }

    @Override
    public StyleAttribute visitWidgetColor(QLSParser.WidgetColorContext ctx) {
        String hexColor = ctx.value.getText();
        return new StyleAttributeColor(ctx.getStart(), hexColor);
    }
}
