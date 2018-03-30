package QLS.QLSVisitor;


import QLS.ParseObjectQLS.StyleAttribute.*;
import QLS.QLSAntlrGen.*;


public class StyleVisitor extends QLSBaseVisitor {

    @Override
    public Style visitWidthStyle(QLSParser.WidthStyleContext ctx) {
        return new Width(Integer.parseInt(ctx.INTEGER().getText()), ctx.getStart().getLine());
    }
    @Override
    public Style visitFontStyle(QLSParser.FontStyleContext ctx) {
        return new Font(ctx.STRING().getText(), ctx.getStart().getLine());
    }
    @Override
    public Style visitFontSizeStyle(QLSParser.FontSizeStyleContext ctx) {
        return new FontSize(Integer.parseInt(ctx.INTEGER().getText()), ctx.getStart().getLine());
    }

    @Override
    public Style visitColorStyle(QLSParser.ColorStyleContext ctx) {
        return new Color(ctx.HEXVALUE().getText(), ctx.getStart().getLine());
    }

}
