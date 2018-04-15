package QLS.QLSVisitor;

import QLS.AST.Page;
import QLS.AST.Stylesheet;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class StylesheetVisitor extends QLSBaseVisitor<Stylesheet> {

    @Override
    public Stylesheet visitHead(QLSParser.HeadContext ctx){

        int line = ctx.getStart().getLine();
        ArrayList<Page> pages = new ArrayList<>();
        PageVisitor pageVisitor = new PageVisitor();
        for(QLSParser.PageContext pageCtx : ctx.block().page()){
            Page page = pageVisitor.visitPage(pageCtx);
            pages.add(page);
        }

        return new Stylesheet(pages, ctx.IDENTIFIER().getText(), line);
    }
}
