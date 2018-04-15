package QLS.QLSVisitor;

import QLS.AST.Statements.QLSQuestion;
import QLS.AST.Widgets.Widget;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

public class QLSQuestionVisitor extends QLSBaseVisitor<QLSQuestion> {

    @Override
    public QLSQuestion visitQuestion(QLSParser.QuestionContext ctx){
        int line = ctx.getStart().getLine();
        Widget widget = null;
        WidgetVisitor widgetvisitor = new WidgetVisitor();


        if(ctx.widget() != null){
            widget = (Widget) widgetvisitor.visit(ctx.widget());
        }

        return new QLSQuestion(ctx.IDENTIFIER().getText(), widget, line);
    }
}