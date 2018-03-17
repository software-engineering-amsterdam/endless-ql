package qls.visitor;

import qls.model.Default;
import qls.model.Section;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

class VisitorBlock<T> extends QLSBaseVisitor<T> {

    // getSections and getDefaults is used by both VisitorPage and VisitorSection
    // so put them in parent class

    List<Section> getSections(List<QLSParser.SectionContext> sectionContextList) {
        List<Section> sections = new ArrayList<>();
        if (sectionContextList == null) {
            return sections;
        }

        VisitorSection visitorSection = new VisitorSection();
        for (QLSParser.SectionContext sectionContext : sectionContextList) {
            Section section = visitorSection.visitSection(sectionContext);
            sections.add(section);
        }

        return sections;
    }

    List<Default> getDefaults(List<QLSParser.Default_Context> defaultContextList) {
        List<Default> defaults = new ArrayList<>();
        if (defaultContextList == null) {
            return defaults;
        }

        VisitorDefault visitorDefault = new VisitorDefault();
        for (QLSParser.Default_Context defaultContext : defaultContextList) {
            Default default_ = visitorDefault.visitDefault_(defaultContext);
            defaults.add(default_);
        }

        return defaults;
    }
}
