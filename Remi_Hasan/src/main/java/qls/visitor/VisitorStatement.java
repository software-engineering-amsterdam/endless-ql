package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.Statement;

public class VisitorStatement extends QLSBaseVisitor<Statement> {

    @Override
    public Statement visitSection(QLSParser.SectionContext ctx) {
        VisitorSection visitorSection = new VisitorSection();
        return visitorSection.visitSection(ctx);
    }

    @Override
    public Statement visitQuestion(QLSParser.QuestionContext ctx) {
        VisitorQuestion visitorQuestion = new VisitorQuestion();
        return visitorQuestion.visitQuestion(ctx);
    }

    @Override
    public Statement visitDefaultStyle(QLSParser.DefaultStyleContext ctx) {
        VisitorDefault visitorDefault = new VisitorDefault();
        return visitorDefault.visitDefaultStyle(ctx);
    }
}
