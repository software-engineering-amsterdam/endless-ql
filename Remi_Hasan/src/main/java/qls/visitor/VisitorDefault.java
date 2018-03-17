package qls.visitor;

import ql.model.expression.ReturnType;
import qls.model.DefaultStyle;
import qls.model.widgets.Widget;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorDefault extends QLSBaseVisitor<DefaultStyle> {

    @Override
    public DefaultStyle visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        VisitorWidget visitorWidget = new VisitorWidget();
        ReturnType returnType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        List<Widget> widgets = new ArrayList<>();
        for (QLSParser.WidgetContext widgetContext : ctx.widget()) {
            Widget widget = visitorWidget.visit(widgetContext);
            widgets.add(widget);
        }

        return new DefaultStyle(ctx.getStart(), returnType, widgets);
    }
}
