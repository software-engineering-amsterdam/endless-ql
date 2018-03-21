package qlviz.interpreter.style;

import com.google.inject.Inject;
import qlviz.QLSBaseVisitor;
import qlviz.QLSVisitor;
import qlviz.QLSParser;
import qlviz.model.style.Question;
import qlviz.model.style.Widget;

public class QuestionVisitor extends QLSBaseVisitor<Question> {

    private final QLSVisitor<Widget> widgetTypeVisitor;

    @Inject
    public QuestionVisitor(QLSVisitor<Widget> widgetTypeVisitor) {
        this.widgetTypeVisitor = widgetTypeVisitor;
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        if (ctx.widget() == null) {
            return new Question(ctx.IDENTIFIER().getText(), ctx);
        }
        else
        {
            return new Question(
                    ctx.IDENTIFIER().getText(),
                    widgetTypeVisitor.visitWidget(ctx.widget()),
                    ctx
            );
        }
    }
}
