package AST;

import Nodes.*;
import AST.gen.*;
import Nodes.Term.Boolean;
import Nodes.Term.Term;
import com.sun.istack.internal.NotNull;
import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FormReader {

    public QLForm parseFile(String path) throws IOException {

        CharStream charStream = CharStreams.fromFileName(path);

        return parseCharstream(charStream);

    }

    public QLForm parseString(String s) {

        CharStream charStream = CharStreams.fromString(s);

        return parseCharstream(charStream);

    }

    public QLForm parseCharstream(CharStream charStream) {

        QLLexer lexer = new QLLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        FormVisitor formVisitor = new FormVisitor();

        QLForm traverseResult = formVisitor.visit(parser.form());

        return traverseResult;

    }

    private static class FormVisitor extends QLBaseVisitor<QLForm> {

        @Override
        public QLForm visitForm(@NotNull QLParser.FormContext ctx) {
            String formName = ctx.VARIABLE().getText();
            QuestionVisitor questionVisitor = new QuestionVisitor();
            ConditionVisitor conditionVisitor = new ConditionVisitor();

            List<Question> questions = ctx.question()
                    .stream()
                    .map(question -> question.accept(questionVisitor))
                    .collect(toList());

            List<Condition> conditions = ctx.condition()
                    .stream()
                    .map(condition -> condition.accept(conditionVisitor))
                    .collect(toList());

            return new QLForm(formName, questions, conditions);
        }
    }

    private static class QuestionVisitor extends QLBaseVisitor<Question> {

        @Override
        public Question visitQuestion(@NotNull QLParser.QuestionContext ctx) {
            String questionName = ctx.VARIABLE().getText();
            String questionLabel = ctx.STRING().getText();
            String questionType = ctx.TYPE().getText();

            ExpressionVisitor expressionVisitor = new ExpressionVisitor();

            Expression expression = expressionVisitor.visitExpression(ctx.expression());

            return new Question(questionName, questionLabel, questionType, expression);
        }
    }

    private static class ConditionVisitor extends QLBaseVisitor<Condition> {

        @Override
        public Condition visitCondition(@NotNull QLParser.ConditionContext ctx){
            QuestionVisitor questionVisitor = new QuestionVisitor();
            ConditionVisitor conditionVisitor = new ConditionVisitor();
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();

            Expression expression = expressionVisitor.visitExpression(ctx.expression());

            List<Question> questions = ctx.question()
                    .stream()
                    .map(question -> question.accept(questionVisitor))
                    .collect(toList());

            List<Condition> conditions = ctx.condition()
                    .stream()
                    .map(condition -> condition.accept(conditionVisitor))
                    .collect(toList());

            Condition condition = new Condition(expression, questions, conditions);

            return  condition;
        }
    }

    private static class ExpressionVisitor extends QLBaseVisitor<Expression> {

        @Override
        public Expression visitExpression(@NotNull QLParser.ExpressionContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            TermVisitor termVisitor = new TermVisitor();

            List<QLParser.ExpressionContext> expressions = ctx.expression();

            if(expressions.size() <= 0)
                return new Expression(termVisitor.visitTerm(ctx.term()));

            // TODO return appropriate elements



            Expression expression = new Expression(); //TODO
        }
    }

    private static class TermVisitor extends QLBaseVisitor<Term>{

        @Override
        public Term visitTerm(@NotNull QLParser.TermContext ctx) {
            return new Boolean(true); //TODO
        }

    }
}