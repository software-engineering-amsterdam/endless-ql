package QLS.parsing.visitors;

import QLS.classes.Question;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class StylesheetVisitor extends QLSBaseVisitor {
    private boolean isVisible;
    private QLSParser.StylesheetContext stylesheet;
    public LinkedHashMap<String, Question> questionMap;
    private QLS.parsing.visitors.BlockVisitor blockVisitor;


    public StylesheetVisitor(QLSParser.StylesheetContext stylesheet){
        this.stylesheet = stylesheet;
        this.questionMap = new LinkedHashMap<>();
        this.blockVisitor = new QLS.parsing.visitors.BlockVisitor(questionMap, isVisible);
        visitStylesheet(this.stylesheet);
    }

    // Node visitor
    @Override
    public Object visitStylesheet(QLSParser.StylesheetContext ctx) {
        visitPage(ctx.page());
        return questionMap;
    }

    // Page visitor
    @Override
    public Object visitPage(QLSParser.PageContext ctx) {
        blockVisitor.visitBlock(ctx.block());
        return questionMap;
    }

}