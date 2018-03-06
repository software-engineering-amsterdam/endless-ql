package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.Page;
import qlviz.model.style.Stylesheet;

import java.util.stream.Collectors;

public class StylesheetVisitor extends QLSBaseVisitor<Stylesheet> {

    private final QLSBaseVisitor<Page> pageVisitor;

    public StylesheetVisitor(QLSBaseVisitor<Page> pageVisitor) {
        this.pageVisitor = pageVisitor;
    }

    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        return new Stylesheet(
                ctx.page()
                        .stream()
                        .map(pageVisitor::visitPage)
                        .collect(Collectors.toList()),
                ctx.IDENTIFIER().getText());
    }
}
