package QLS.QLSVisitor;

import QLS.ParseObjectQLS.QLSQuestion;
import QLS.ParseObjectQLS.Widgets.Widget;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

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
