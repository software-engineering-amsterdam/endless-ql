package visitor;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.Section;

public class VisitorSection extends QLSBaseVisitor<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        // TODO
        return new Section();
    }
}
