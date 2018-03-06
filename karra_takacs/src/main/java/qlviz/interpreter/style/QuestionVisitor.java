package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.Question;
import qlviz.model.style.WidgetType;

public class QuestionVisitor extends QLSBaseVisitor<Question> {

    private final QLSBaseVisitor<WidgetType> widgetTypeVisitor;

    public QuestionVisitor(QLSBaseVisitor<WidgetType> widgetTypeVisitor) {
        this.widgetTypeVisitor = widgetTypeVisitor;
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        if (ctx.widgetType() == null) {
            return new Question(ctx.IDENTIFIER().getText());
        }
        else
        {
            return new Question(
                    ctx.IDENTIFIER().getText(),
                    widgetTypeVisitor.visitWidgetType(ctx.widgetType())
            );
        }
    }
}
