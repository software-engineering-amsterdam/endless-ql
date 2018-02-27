package visitor;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.Page;
import model.StyleSheet;

import java.util.ArrayList;

public class VisitorStyleSheet extends QLSBaseVisitor<StyleSheet> {
    // TODO

    @Override
    public StyleSheet visitRoot(QLSParser.RootContext ctx) {
        VisitorPage visitorPage = new VisitorPage();

        ArrayList<Page> pages = new ArrayList<>();
        for (QLSParser.PageContext pageContext : ctx.page()) {
            // TODO parse page
            Page page = visitorPage.visitPage(pageContext);
            pages.add(page);
        }

        return new StyleSheet(ctx.IDENTIFIER().getText(), pages);
    }
}
