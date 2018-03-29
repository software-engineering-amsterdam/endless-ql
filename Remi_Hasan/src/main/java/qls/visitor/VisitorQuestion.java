package qls.visitor;

import qls.model.statement.QuestionReference;
import qls.model.widget.Widget;
import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.widget.WidgetDefault;

public class VisitorQuestion extends QLSBaseVisitor<QuestionReference> {

    @Override
    public QuestionReference visitQuestion(QLSParser.QuestionContext ctx) {
        VisitorWidget visitorWidget = new VisitorWidget();

        Widget widget = null;
        if (ctx.widget() != null) {
            widget = visitorWidget.visit(ctx.widget());
        }

        return new QuestionReference(ctx.getStart(), ctx.identifier.getText(), widget);
    }
}
