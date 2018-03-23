package QLS.parsing.visitors;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.Element;
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
    public Element visitElement(QLSParser.ElementContext ctx) {
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
        List<Element> elements = new ArrayList<>();
        for (QLSParser.ElementContext c : ctx.element()) {
            elements.add(this.visitElement(c));
        }
        return new Section(id, elements);
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Widget widget = widgetVisitor.visitWidget(ctx.widget());
        return new Question(id, widget);
    }
}