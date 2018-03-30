package ql.builder;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.statement.Statement;

public class StatementBuilder extends QLBaseVisitor<Statement> {

    @Override
    public Statement visitCondition(QLParser.ConditionContext ctx) {
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        return conditionBuilder.visitCondition(ctx);
    }

    @Override
    public Statement visitQuestion(QLParser.QuestionContext ctx) {
        QuestionBuilder questionBuilder = new QuestionBuilder();
        return questionBuilder.visitQuestion(ctx);
    }

}
