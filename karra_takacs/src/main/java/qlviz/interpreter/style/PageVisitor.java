package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.Page;
import qlviz.model.style.Section;

import java.util.stream.Collectors;

public class PageVisitor extends QLSBaseVisitor<Page> {

    private final QLSBaseVisitor<Section> sectionVisitor;

    public PageVisitor(QLSBaseVisitor<Section> sectionVisitor) {
        this.sectionVisitor = sectionVisitor;
    }

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        return new Page(
                ctx.IDENTIFIER().getText(),
                ctx.section()
                        .stream()
                        .map(sectionVisitor::visitSection)
                        .collect(Collectors.toList()),
                ctx
        );
    }
}
