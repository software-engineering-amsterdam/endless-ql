package QLS.QLSVisitor;

import QLS.AST.Statements.Default;
import QLS.AST.Statements.QLSQuestion;
import QLS.AST.Statements.Statement;
import QLS.QLSAntlrGen.QLSBaseVisitor;
import QLS.QLSAntlrGen.QLSParser;

import java.util.ArrayList;

public class SectionVisitor extends QLSBaseVisitor {

    @Override
    public ArrayList<Statement> visitSection(QLSParser.SectionContext ctx){

        ArrayList<Statement> statements = new ArrayList<>();

        SectionVisitor sectionVisitor = new SectionVisitor();
        DefaultVisitor defaultVisitor = new DefaultVisitor();
        QLSQuestionVisitor questionVisitor = new QLSQuestionVisitor();

        for(QLSParser.SectionContext secCtx: ctx.section()){
            ArrayList<Statement> sectionStatements = sectionVisitor.visitSection(secCtx);
            statements.addAll(sectionStatements);
        }

        for(QLSParser.DefaultSecContext defSecCtx: ctx.defaultSec()){
            Default defaultSection = defaultVisitor.visitDefaultSec(defSecCtx);
            statements.add(defaultSection);
        }

        for(QLSParser.QuestionContext questionCtx : ctx.question() ){
            QLSQuestion question = questionVisitor.visitQuestion(questionCtx);
            statements.add(question);
        }

        return statements;
    }
}
