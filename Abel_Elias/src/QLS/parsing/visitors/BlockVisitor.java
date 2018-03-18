package QLS.parsing.visitors;
import QL.classes.values.Value;
import QLS.classes.Question;
import QLS.classes.widgets.Widget;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlockVisitor extends QLSBaseVisitor {
    private boolean isVisible;
    private WidgetVisitor widgetVisitor;
    private LinkedHashMap<String, Question> questionMap;


    public BlockVisitor(LinkedHashMap<String, Question> questionMap, boolean isVisible){
        this.questionMap = questionMap;
        this.isVisible = isVisible;
        this.widgetVisitor = new WidgetVisitor();
    }

    @Override
    public Object visitDefaultWidget(QLSParser.DefaultWidgetContext ctx) {
        return questionMap;
    }

    @Override
    public Object visitSection(QLSParser.SectionContext ctx) {
        return questionMap;
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Object widget = visitWidget(ctx.widget());
        Question question = new Question(id, (Widget) widget);
        questionMap.put(id, question);
        return questionMap;
    }

    @Override
    public Widget visitWidget(QLSParser.WidgetContext ctx) {
        return new Widget("new");
    }
}