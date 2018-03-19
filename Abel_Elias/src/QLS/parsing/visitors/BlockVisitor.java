package QLS.parsing.visitors;
import QL.classes.values.Value;
import QLS.classes.Block;
import QLS.classes.Element;
import QLS.classes.Question;
import QLS.classes.Section;
import QLS.classes.widgets.Widget;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BlockVisitor extends QLSBaseVisitor {
    private WidgetVisitor widgetVisitor;

    public BlockVisitor(){
        this.widgetVisitor = new WidgetVisitor();
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Block> blocks = new ArrayList<>();
        for (QLSParser.BlockContext c : ctx.block()) {
            blocks.add((Block) this.visitBlock(c));
        }
        return new Section(id,blocks);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Object widget = visitWidget(ctx.widget());
        return new Question(id, (Widget) widget);
    }

    @Override
    public Widget visitWidget(QLSParser.WidgetContext ctx) {
        return new Widget("new");
    }
}