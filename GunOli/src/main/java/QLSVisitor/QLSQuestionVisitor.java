package QLSVisitor;

import ParseObjectQLS.QLSQuestion;
import ParseObjectQLS.Widgets.Widget;
import QLSAntlrGen.QLSBaseVisitor;
import QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class QLSQuestionVisitor extends QLSBaseVisitor<QLSQuestion> {


    @Override
    public QLSQuestion visitQuestion(QLSParser.QuestionContext ctx){
        ArrayList<Widget> widgets = new ArrayList<>();
        WidgetVisitor widgetvisitor = new WidgetVisitor();


        for( QLSParser.WidgetContext widgetContext : ctx.widget()){
            Widget widget = (Widget) widgetvisitor.visit(widgetContext);
            widgets.add(widget);
        }

        return new QLSQuestion(ctx.IDENTIFIER().getText(), widgets);
    }
}
