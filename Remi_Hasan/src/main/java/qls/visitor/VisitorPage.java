package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;
import qls.antlr.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorPage extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        // Get this page's sections
        VisitorSection visitorSection = new VisitorSection();
        List<Section> sections = new ArrayList<>();
        for(QLSParser.SectionContext sectionContext : ctx.section()) {
            sections.add(visitorSection.visitSection(sectionContext));
        }

        // Get this page's default styles
        VisitorDefault visitorDefault = new VisitorDefault();
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        for(QLSParser.DefaultStyleContext defaultStyleContext : ctx.defaultStyle()) {
            defaultStyles.add(visitorDefault.visitDefaultStyle(defaultStyleContext));
        }

        return new Page(ctx.getStart(), ctx.identifier.getText(), sections, defaultStyles);
    }
}