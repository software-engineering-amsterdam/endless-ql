package qls.visitor;

import qls.model.DefaultStyle;
import qls.model.Section;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

class VisitorBlock<T> extends QLSBaseVisitor<T> {

    // getSections and getDefaults is used by both VisitorPage and VisitorSection
    // so put them in parent class

    List<Section> getSections(List<QLSParser.SectionContext> sectionContexts) {
        List<Section> sections = new ArrayList<>();
        if (sectionContexts == null) {
            return sections;
        }

        VisitorSection visitorSection = new VisitorSection();
        for (QLSParser.SectionContext sectionContext : sectionContexts) {
            Section section = visitorSection.visitSection(sectionContext);
            sections.add(section);
        }

        return sections;
    }

    List<DefaultStyle> getDefaults(List<QLSParser.DefaultStyleContext> defaultStyleContexts) {
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        if (defaultStyleContexts == null) {
            return defaultStyles;
        }

        VisitorDefault visitorDefault = new VisitorDefault();
        for (QLSParser.DefaultStyleContext defaultContext : defaultStyleContexts) {
            DefaultStyle defaultStyle = visitorDefault.visitDefaultStyle(defaultContext);
            defaultStyles.add(defaultStyle);
        }

        return defaultStyles;
    }
}
