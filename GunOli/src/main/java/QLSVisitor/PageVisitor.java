package QLSVisitor;

import ParseObjectQLS.Default;
import ParseObjectQLS.Page;
import ParseObjectQLS.Section;
import QLSAntlrGen.QLSBaseVisitor;
import QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class PageVisitor extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx){

        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<Default> defaultSections = new ArrayList<Default>();

        SectionVisitor sectionVisitor = new SectionVisitor();
        DefaultVisitor defaultVisitor = new DefaultVisitor();

        for(QLSParser.SectionContext secCtx: ctx.section()){
            Section section = sectionVisitor.visitSection(secCtx);
            sections.add(section);

        }

        for(QLSParser.DefaultSecContext defSecCtx: ctx.defaultSec()){
            Default defaultSection = defaultVisitor.visitDefaultSec(defSecCtx);
            defaultSections.add(defaultSection);

        }
        return new Page(sections, defaultSections, ctx.IDENTIFIER().getText());

    }


}
