package QLS.parsing.visitors;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.LineInBlock;
import QLS.classes.blocks.Question;
import QLS.classes.blocks.Section;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.DropdownWidget;
import QLS.classes.widgets.RadioWidget;
import QLS.classes.widgets.SliderWidget;
import QLS.classes.widgets.SpinBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.Widget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class BlockVisitor extends QLSBaseVisitor {

    private WidgetVisitor widgetVisitor;


    public BlockVisitor() {
        this.widgetVisitor = new WidgetVisitor();
    }

    @Override
    public Block visitBlock(QLSParser.BlockContext ctx) {
        List<LineInBlock> blockElements = new ArrayList<>();
        for (QLSParser.LineInBlockContext c : ctx.lineInBlock()) {
            blockElements.add(this.visitLineInBlock(c));
        }
        return new Block(blockElements);
    }

    @Override
    public LineInBlock visitLineInBlock(QLSParser.LineInBlockContext ctx) {
        if (ctx.section() != null) {
            return visitSection(ctx.section());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        } else {
            return null;
        }
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Block> blocks = new ArrayList<>();
        for (QLSParser.BlockContext c : ctx.block()) {
            blocks.add(this.visitBlock(c));
        }
        return new Section(id, blocks);
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Widget widget = widgetVisitor.visitWidget(ctx.widget());
        return new Question(id, widget);
    }
}