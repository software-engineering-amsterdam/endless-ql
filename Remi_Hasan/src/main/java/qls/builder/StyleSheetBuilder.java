package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.Page;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public class StyleSheetBuilder extends QLSBaseVisitor<StyleSheet> {

    @Override
    public StyleSheet visitRoot(QLSParser.RootContext ctx) {
        PageBuilder pageBuilder = new PageBuilder();

        List<Page> pages = new ArrayList<>();
        for (QLSParser.PageContext pageContext : ctx.page()) {
            Page page = pageBuilder.visitPage(pageContext);
            pages.add(page);
        }

        StyleSheet styleSheet = new StyleSheet(ctx.identifier.getText(), pages);
        styleSheet.setToken(ctx.getStart());
        return styleSheet;
    }
}
