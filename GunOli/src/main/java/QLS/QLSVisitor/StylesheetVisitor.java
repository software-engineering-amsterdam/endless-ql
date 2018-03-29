package QLS.QLSVisitor;

import QLS.ParseObjectQLS.Page;
import QLS.ParseObjectQLS.Stylesheet;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

import java.util.ArrayList;


public class StylesheetVisitor extends QLSBaseVisitor<Stylesheet> {

    private WidgetTable widgetTable;

    public StylesheetVisitor(WidgetTable widgetTable){
        this.widgetTable = widgetTable;
    }

    @Override
    public Stylesheet visitHead(QLSParser.HeadContext ctx){

        int line = ctx.getStart().getLine();
        ArrayList<Page> pages = new ArrayList<>();
        PageVisitor pageVisitor = new PageVisitor();
        for(QLSParser.PageContext pageCtx : ctx.block().page()){
            Page page = pageVisitor.visitPage(pageCtx);
            pages.add(page);
        }

        return new Stylesheet(pages, ctx.IDENTIFIER().getText(), widgetTable, line);
    }
}
