package qls.visitor;

import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;
import qls.model.Page;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public class VisitorStyleSheet extends QLSBaseVisitor<StyleSheet> {
    // TODO

    @Override
    public StyleSheet visitRoot(QLSParser.RootContext ctx) {
        VisitorPage visitorPage = new VisitorPage();

        List<Page> pages = new ArrayList<>();
        for (QLSParser.PageContext pageContext : ctx.page()) {
            // TODO parse page
            Page page = visitorPage.visitPage(pageContext);
            pages.add(page);
        }

        return new StyleSheet(ctx.IDENTIFIER().getText(), pages);
    }
}