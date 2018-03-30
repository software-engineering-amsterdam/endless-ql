package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.statement.QuestionReference;
import qls.model.widget.Widget;

public class QuestionReferenceBuilder extends QLSBaseVisitor<QuestionReference> {

    @Override
    public QuestionReference visitQuestionReference(QLSParser.QuestionReferenceContext ctx) {
        WidgetBuilder visitorWidget = new WidgetBuilder();

        Widget widget = null;
        if (ctx.widget() != null) {
            widget = visitorWidget.visit(ctx.widget());
        }

        QuestionReference questionReference = new QuestionReference(ctx.identifier.getText(), widget);
        questionReference.setToken(ctx.getStart());
        return questionReference;
    }
}
