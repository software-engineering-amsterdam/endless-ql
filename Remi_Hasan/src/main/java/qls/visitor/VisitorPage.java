package qls.visitor;

import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;
import ql.model.stylesheet.Default;
import ql.model.stylesheet.Page;
import ql.model.stylesheet.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorPage extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        VisitorDefault visitorDefault = new VisitorDefault();
        VisitorSection visitorSection = new VisitorSection();

        // Visit defaults
        List<Default> defaults = new ArrayList<>();
        for (QLSParser.Default_Context default_context : ctx.default_()) {
            Default default_ = visitorDefault.visitDefault_(default_context);
            defaults.add(default_);
        }

        // Visit sections
        List<Section> sections = new ArrayList<>();
        for (QLSParser.SectionContext sectionContext : ctx.section()) {
            Section section = visitorSection.visitSection(sectionContext);
            sections.add(section);
        }

        return new Page(ctx.IDENTIFIER().getText(), defaults, sections);
    }
}