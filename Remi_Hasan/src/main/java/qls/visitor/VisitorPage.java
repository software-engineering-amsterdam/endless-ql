package qls.visitor;

import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;
import qls.antlr.QLSParser;

import java.util.List;

public class VisitorPage extends VisitorBlock<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        List<DefaultStyle> defaultStyles = this.getDefaults(ctx.defaultStyle());
        List<Section> sections = this.getSections(ctx.section());
        return new Page(ctx.getStart(), ctx.IDENTIFIER().getText(), defaultStyles, sections);
    }
}