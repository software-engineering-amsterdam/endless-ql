package qlviz.interpreter.style;

import qlviz.QLBaseVisitor;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.Page;
import qlviz.model.style.Section;

import java.util.stream.Collectors;

public class PageVisitor extends QLSBaseVisitor<Page> {

    private final QLSBaseVisitor<Section> sectionVisitor;
    private final QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor;

    public PageVisitor(QLSBaseVisitor<Section> sectionVisitor, QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor) {
        this.sectionVisitor = sectionVisitor;
        this.defaultWidgetVisitor = defaultWidgetVisitor;
    }

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        return new Page(
                ctx.IDENTIFIER().getText(),
                ctx.section()
                        .stream()
                        .map(sectionVisitor::visitSection)
                        .collect(Collectors.toList()),
                ctx.defaultWidgetDeclaration()
                        .stream()
                        .map(defaultWidgetVisitor::visitDefaultWidgetDeclaration)
                        .collect(Collectors.toList()),
                ctx
        );
    }
}
