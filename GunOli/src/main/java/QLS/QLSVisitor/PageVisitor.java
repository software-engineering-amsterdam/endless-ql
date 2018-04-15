package QLS.QLSVisitor;

import QLS.AST.Page;
import QLS.AST.Statements.Default;
import QLS.AST.Statements.Statement;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class PageVisitor extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx){

        int line = ctx.getStart().getLine();
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Default> defaultSections = new ArrayList<Default>();

        SectionVisitor sectionVisitor = new SectionVisitor();
        DefaultVisitor defaultVisitor = new DefaultVisitor();

        for(QLSParser.SectionContext secCtx: ctx.section()){
            ArrayList<Statement> sectionStatements = sectionVisitor.visitSection(secCtx);
            statements.addAll(sectionStatements);
        }

        for(QLSParser.DefaultSecContext defSecCtx: ctx.defaultSec()){
            Default defaultSection = defaultVisitor.visitDefaultSec(defSecCtx);
            defaultSections.add(defaultSection);
        }

        return new Page(statements, ctx.IDENTIFIER().getText(), line);
    }


}
