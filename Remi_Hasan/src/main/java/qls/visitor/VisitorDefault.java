package qls.visitor;

import ql.model.expression.ReturnType;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorDefault extends QLSBaseVisitor<DefaultStyle> {

    @Override
    public DefaultStyle visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        ReturnType returnType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        // Default widget type defined by user
        Widget widget = null;
        VisitorWidget visitorWidget = new VisitorWidget();

        // StyleAttribute attributes defined by user
        List<StyleAttribute> styleAttributes = new ArrayList<>();
        VisitorStyle visitorStyle = new VisitorStyle();
        for(QLSParser.WidgetOrAttributeContext widgetOrAttributeContext : ctx.singleOrMultipleWidgetOrAttribute().widgetOrAttribute()) {
            if(widgetOrAttributeContext.styleAttribute() != null){
                StyleAttribute styleAttribute = visitorStyle.visit(widgetOrAttributeContext.styleAttribute());
                styleAttributes.add(styleAttribute);
            } else if(widgetOrAttributeContext.widget() != null){
                widget = visitorWidget.visit(widgetOrAttributeContext.widget());
            }
        }

        return new DefaultStyle(ctx.getStart(), returnType, styleAttributes, widget);
    }
}
