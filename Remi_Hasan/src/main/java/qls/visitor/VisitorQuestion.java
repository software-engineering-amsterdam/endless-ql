package qls.visitor;

import qls.model.Question;
import qls.model.widgets.Widget;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

public class VisitorQuestion extends QLSBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        VisitorWidget visitorWidget = new VisitorWidget();

        Widget widget = null;
        if (ctx.widget() != null) {
            widget = visitorWidget.visit(ctx.widget());
        }

        return new Question(ctx.getStart(), ctx.IDENTIFIER().getText(), widget);
    }
}
