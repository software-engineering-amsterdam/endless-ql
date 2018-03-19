package QLS.parsing.visitors;

import QLS.classes.Block;
import QLS.classes.Page;
import QLS.classes.Question;
import QLS.classes.Section;
import QLS.classes.Stylesheet;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class StylesheetVisitor extends QLSBaseVisitor {
    private boolean isVisible;
    private QLS.parsing.visitors.BlockVisitor blockVisitor;


    public StylesheetVisitor(){
        this.blockVisitor = new QLS.parsing.visitors.BlockVisitor();
    }

    // Node visitor
    @Override
    public Stylesheet visitStylesheet(QLSParser.StylesheetContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Page> pages = new ArrayList<>();
        for (QLSParser.PageContext c : ctx.page()) {
            pages.add(visitPage(c));
        }
        return new Stylesheet(id, pages);
    }

    // Page visitor
    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Block> blocks = new ArrayList<>();

        for (QLSParser.BlockContext c : ctx.block()) {
            blocks.add((Block) blockVisitor.visitBlock(c));
        }
        return new Page(id, blocks);
    }

}