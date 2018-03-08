package visitor.stylesheet;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.expression.ReturnType;
import model.stylesheet.Default;
import model.stylesheet.widgets.Widget;

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
