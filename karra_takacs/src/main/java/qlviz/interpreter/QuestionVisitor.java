package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.Question;
import qlviz.model.QuestionType;

public class QuestionVisitor extends QLBaseVisitor<Question> {

    private final QLBaseVisitor<QuestionType> questionTypeVisitor;

    public QuestionVisitor(QLBaseVisitor<QuestionType> questionTypeVisitor) {
        this.questionTypeVisitor = questionTypeVisitor;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        return new Question(
                ctx.questionName().getText(),
                ctx.questionText().getText(),
                questionTypeVisitor.visitTerminal(ctx.TYPE()));
    }
}
