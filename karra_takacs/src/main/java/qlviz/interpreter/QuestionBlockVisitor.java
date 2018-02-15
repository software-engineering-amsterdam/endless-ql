package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.QuestionBlock;

import java.util.stream.Collectors;

public class QuestionBlockVisitor extends QLBaseVisitor<QuestionBlock> {

    private final QuestionVisitor questionVisitor;
    private final ConditionalBlockVisitor conditionalBlockVisitor;

    public QuestionBlockVisitor(QuestionVisitor questionVisitor, ConditionalBlockVisitor conditionalBlockVisitor) {
        this.questionVisitor = questionVisitor;
        this.conditionalBlockVisitor = conditionalBlockVisitor;
    }

    @Override
    public QuestionBlock visitQuestionBlock(QLParser.QuestionBlockContext ctx) {
        return new QuestionBlock(
               ctx.question().stream().map(questionVisitor::visitQuestion).collect(Collectors.toList()),
               ctx.conditionalBlock().stream().map(conditionalBlockVisitor::visitConditionalBlock).collect(Collectors.toList())
        );
    }
}
