package visitor;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.Page;

public class VisitorPage extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        // TODO more child parsing
        return new Page(ctx.IDENTIFIER().getText());
    }
}
