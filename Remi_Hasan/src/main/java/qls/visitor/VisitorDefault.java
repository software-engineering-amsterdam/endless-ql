package qls.visitor;

import ql.model.expression.ReturnType;
import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.statement.DefaultStyle;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class VisitorDefault extends QLSBaseVisitor<DefaultStyle> {

    @Override
    public DefaultStyle visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        ReturnType returnType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        // StyleAttribute attributes defined by user
        List<StyleAttribute> styleAttributes = this.getStyles(ctx.styleAttribute());

        // Default widget type defined by user
        VisitorWidget visitorWidget = new VisitorWidget();
        Widget widget = null;
        if (ctx.widget() != null) {
            widget = visitorWidget.visit(ctx.widget());
        }

        DefaultStyle defaultStyle = new DefaultStyle(returnType, styleAttributes, widget);
        defaultStyle.setToken(ctx.getStart());
        return defaultStyle;
    }

    private List<StyleAttribute> getStyles(List<QLSParser.StyleAttributeContext> styleAttributeContexts) {
        List<StyleAttribute> styleAttributes = new ArrayList<>();
        VisitorStyleAttribute visitorStyleAttribute = new VisitorStyleAttribute();
        for (QLSParser.StyleAttributeContext styleAttributeContext : styleAttributeContexts) {
            StyleAttribute styleAttribute = visitorStyleAttribute.visit(styleAttributeContext);
            styleAttributes.add(styleAttribute);
        }

        return styleAttributes;
    }
}
