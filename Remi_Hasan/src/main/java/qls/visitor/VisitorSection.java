package qls.visitor;

import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;
import ql.model.stylesheet.Default;
import ql.model.stylesheet.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorSection extends QLSBaseVisitor<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        VisitorDefault visitorDefault = new VisitorDefault();
        VisitorSection visitorSection = new VisitorSection();

        String identifier = ctx.STRING().getText();
        // Strip quotes
        identifier = identifier.substring(1, identifier.length() - 1);

        // Visit subsections
        List<Section> sections = new ArrayList<>();
        if (ctx.section() != null) {
            for (QLSParser.SectionContext sectionContext : ctx.section()) {
                Section section = visitorSection.visitSection(sectionContext);
                sections.add(section);
            }
        }

        // Visit defaults
        List<Default> defaults = new ArrayList<>();
        if (ctx.default_() != null) {
            for (QLSParser.Default_Context default_context : ctx.default_()) {
                Default default_ = visitorDefault.visitDefault_(default_context);
                defaults.add(default_);
            }
        }

        return new Section(identifier, sections, defaults);
    }
}
