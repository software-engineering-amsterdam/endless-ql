package visitor.stylesheet;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.stylesheet.Default;
import model.stylesheet.Page;
import model.stylesheet.Section;

import java.util.ArrayList;

public class VisitorPage extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        VisitorDefault visitorDefault = new VisitorDefault();
        VisitorSection visitorSection = new VisitorSection();

        // Visit defaults
        ArrayList<Default> defaults = new ArrayList<>();
        for(QLSParser.Default_Context default_context : ctx.default_()){
            Default default_ = visitorDefault.visitDefault_(default_context);
            defaults.add(default_);
        }

        // Visit sections
        ArrayList<Section> sections = new ArrayList<>();
        for(QLSParser.SectionContext sectionContext: ctx.section()){
            Section section = visitorSection.visitSection(sectionContext);
            sections.add(section);
        }

        return new Page(ctx.IDENTIFIER().getText(), defaults, sections);
    }
}