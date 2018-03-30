package qlviz.interpreter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import qlviz.QLBaseVisitor;
import qlviz.QLVisitor;
import qlviz.QLParser;
import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import java.util.stream.Collectors;

public class ConditionalBlockVisitor extends QLBaseVisitor<ConditionalBlock> {

    private final QLVisitor<BooleanExpression> booleanExpressionVisitor;
    private final QLVisitor<QuestionBlock> questionBlockVisitor;

    @Inject
    public ConditionalBlockVisitor(
            QLVisitor<BooleanExpression> booleanExpressionVisitor,
            @Assisted QLVisitor<QuestionBlock> questionBlockVisitor) {
        this.booleanExpressionVisitor = booleanExpressionVisitor;
        this.questionBlockVisitor = questionBlockVisitor;
    }

    @Override
    public ConditionalBlock visitConditionalBlock(QLParser.ConditionalBlockContext ctx) {
        return new ConditionalBlock(
                booleanExpressionVisitor.visitBooleanExpression(ctx.booleanExpression()),
                ctx.questionBlock().stream().map(questionBlockVisitor::visitQuestionBlock).collect(Collectors.toList()),
                ctx);
    }
}
