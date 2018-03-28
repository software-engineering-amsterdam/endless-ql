package AST;

import Nodes.*;
import AST.gen.*;
import Nodes.Operator.*;
import Nodes.Term.*;
import com.sun.istack.internal.NotNull;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Reads in a QL from in text format and returns a parsed QLForm object
 */
public class FormReader {
    /**
     * Reads a QL form from a CharStream and returns a parsed QLForm object.
     * @param charStream a charStream instance.
     * @return A parsed QLForm object.
     */
    public QLForm parseCharStream(CharStream charStream) {
        QLLexer lexer = new QLLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        FormVisitor formVisitor = new FormVisitor();

        return formVisitor.visit(parser.form());
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

            if(ctx.expression() == null)
                return new Question(questionName, questionLabel, questionType);
            else {
                ExpressionVisitor expressionVisitor = new ExpressionVisitor();
                return new Question(questionName, questionLabel, questionType, expressionVisitor.visitExpression(ctx.expression()));
            }
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

            return new Condition(expression, questions, conditions);
        }
    }

    private static class ExpressionVisitor extends QLBaseVisitor<Expression> {

        @Override
        public Expression visitExpression(@NotNull QLParser.ExpressionContext ctx) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            TermVisitor termVisitor = new TermVisitor();
            ArithmeticVisitor arithmeticVisitor = new ArithmeticVisitor();
            OperatorVisitor operatorVisitor = new OperatorVisitor();

            List<QLParser.ExpressionContext> expressions = ctx.expression();
            QLParser.TermContext termContext = ctx.term();
            TerminalNode notNode = ctx.NOT();
            QLParser.FactorContext factorContext = ctx.factor();
            QLParser.MuldivContext muldivContext = ctx.muldiv();
            QLParser.AddsubContext addsubContext = ctx.addsub();
            QLParser.OperatorContext operatorContext = ctx.operator();

            // This is an Expression which only catches brackets
            if(expressions.size() == 1 && notNode == null)
                return expressionVisitor.visitExpression(expressions.get(0));

            if(termContext != null)
                return termVisitor.visitTerm(ctx.term());

            if(notNode != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), new Not("!"));

            if(factorContext != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitArithmetic(factorContext));

            if(muldivContext != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitArithmetic(muldivContext));

            if(addsubContext != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitArithmetic(addsubContext));

            if(operatorContext != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), operatorVisitor.visitOperator(operatorContext));

            return null;
        }
    }

    private static class TermVisitor extends QLBaseVisitor<Term> {

        @Override
        public Term visitTerm(@NotNull QLParser.TermContext ctx) {
            TerminalNode bool = ctx.BOOLEAN();
            TerminalNode qlstring = ctx.STRING();
            TerminalNode variable = ctx.VARIABLE();
            TerminalNode integer = ctx.INTEGER();
            TerminalNode decimal = ctx.DECIMAL();

            if(bool != null)
                return new QLBoolean(Boolean.parseBoolean(bool.toString()));

            if(qlstring != null)
                return new QLString(qlstring.toString());

            if(variable != null)
                return new Variable(variable.toString());

            if(integer != null)
                return new QLFloat((Integer.parseInt(integer.toString())));

            if(decimal != null)
                return new QLFloat((Float.parseFloat(decimal.toString())));

            return null;
        }
    }

    private static class ArithmeticVisitor extends QLBaseVisitor<Operator>{
        Operator visitArithmetic(@NotNull QLParser.FactorContext ctx) {
            return new ArithmeticOperation(ctx.getText());
        }

        Operator visitArithmetic(@NotNull QLParser.MuldivContext ctx) {
            return new ArithmeticOperation(ctx.getText());
        }

        Operator visitArithmetic(@NotNull QLParser.AddsubContext ctx) {
            return new ArithmeticOperation(ctx.getText());
        }
    }

    private static class OperatorVisitor extends QLBaseVisitor<Operator>{

        @Override
        public Operator visitOperator(@NotNull QLParser.OperatorContext ctx) {
            QLParser.BooloperatorContext booloperatorContext = ctx.booloperator();
            QLParser.EqualoperatorContext equaloperatorContext = ctx.equaloperator();
            QLParser.ComparisonContext comparisonContext = ctx.comparison();

            if(booloperatorContext != null)
                return new BooleanOperation(booloperatorContext.getText());

            if(equaloperatorContext != null)
                return new EqualOperation(equaloperatorContext.getText());

            if(comparisonContext != null)
                return new ComparisonOperation(comparisonContext.getText());

            return null;
        }
    }
}
