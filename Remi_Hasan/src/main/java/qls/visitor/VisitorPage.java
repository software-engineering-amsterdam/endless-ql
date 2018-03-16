package qls.visitor;

import qls.model.Default;
import qls.model.Page;
import qls.model.Section;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorPage extends VisitorBlock<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        List<Default> defaults = this.getDefaults(ctx.default_());
        List<Section> sections = this.getSections(ctx.section());
        return new Page(ctx.getStart(), ctx.IDENTIFIER().getText(), defaults, sections);
    }
}