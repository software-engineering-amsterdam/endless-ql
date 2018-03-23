package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.Statement;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.Question;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class VisitorStatement extends QLBaseVisitor<Statement> {

    @Override
    public Statement visitCondition(QLParser.ConditionContext ctx) {
        VisitorCondition visitorCondition = new VisitorCondition();
        return visitorCondition.visitCondition(ctx);
    }

    @Override
    public Statement visitQuestion(QLParser.QuestionContext ctx) {
        VisitorQuestion visitorQuestion = new VisitorQuestion();
        return visitorQuestion.visitQuestion(ctx);
    }

}
