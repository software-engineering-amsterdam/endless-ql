package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.statement.Statement;

public class StatementBuilder extends QLSBaseVisitor<Statement> {

    @Override
    public Statement visitSection(QLSParser.SectionContext ctx) {
        SectionBuilder sectionBuilder = new SectionBuilder();
        return sectionBuilder.visitSection(ctx);
    }

    @Override
    public Statement visitQuestionReference(QLSParser.QuestionReferenceContext ctx) {
        QuestionReferenceBuilder questionReferenceBuilder = new QuestionReferenceBuilder();
        return questionReferenceBuilder.visitQuestionReference(ctx);
    }

    @Override
    public Statement visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        DefaultStyleBuilder defaultStyleBuilder = new DefaultStyleBuilder();
        return defaultStyleBuilder.visitDefaultStyle(ctx);
    }
}
