package qls.visitor;

import qls.model.Question;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorQuestion extends QLSBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        VisitorWidget visitorWidget = new VisitorWidget();
        VisitorStyle visitorStyle = new VisitorStyle();

        Widget widget = null;
        List<StyleAttribute> styleAttributes = new ArrayList<>();
        if(ctx.singleOrMultipleWidgetOrAttribute() != null){
            for (QLSParser.WidgetOrAttributeContext widgetOrAttributeContext : ctx.singleOrMultipleWidgetOrAttribute().widgetOrAttribute()) {
                if(widgetOrAttributeContext.widget() != null){
                    widget = visitorWidget.visit(widgetOrAttributeContext.widget());
                } else if(widgetOrAttributeContext.styleAttribute() != null){
                    styleAttributes.add(visitorStyle.visit(widgetOrAttributeContext.styleAttribute()));
                }
            }
        }

        return new Question(ctx.getStart(), ctx.IDENTIFIER().getText(), widget, styleAttributes);
    }
}
