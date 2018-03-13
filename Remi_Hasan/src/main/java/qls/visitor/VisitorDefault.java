package qls.visitor;

import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;
import ql.model.expression.ReturnType;
import qls.model.Default;
import qls.model.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class VisitorDefault extends QLSBaseVisitor<Default> {
    @Override
    public Default visitDefault_(QLSParser.Default_Context ctx) {

        VisitorWidget visitorWidget = new VisitorWidget();

        // TODO
        ReturnType returnType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        List<Widget> widgets = new ArrayList<>();
        for (QLSParser.WidgetContext widgetContext : ctx.widget()) {
            Widget widget = visitorWidget.visit(widgetContext);
            widgets.add(widget);
        }

        return new Default(returnType, widgets);
    }
}
