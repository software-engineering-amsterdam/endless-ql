package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.statement.QuestionReference;
import qls.model.widget.Widget;

public class VisitorQuestion extends QLSBaseVisitor<QuestionReference> {

    @Override
    public QuestionReference visitQuestion(QLSParser.QuestionContext ctx) {
        VisitorWidget visitorWidget = new VisitorWidget();

        Widget widget = null;
        if (ctx.widget() != null) {
            widget = visitorWidget.visit(ctx.widget());
        }

        QuestionReference questionReference = new QuestionReference(ctx.identifier.getText(), widget);
        questionReference.setToken(ctx.getStart());
        return questionReference;
    }
}
