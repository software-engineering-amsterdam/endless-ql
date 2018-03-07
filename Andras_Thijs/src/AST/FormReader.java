package AST;

import Nodes.*;
import AST.gen.*;
import Nodes.Operator.*;
import Nodes.Term.Boolean;
import Nodes.Term.Float;
import Nodes.Term.QLString;
import Nodes.Term.Term;
import Nodes.Term.Variable;
import com.sun.istack.internal.NotNull;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Reads in a QL from in text format and returns a parsed QLForm object
 */
public class FormReader {

    /**
     * Reads a QL form from a text file located at the path and returns a parsed QLForm object
     * @param path
     * @return A parsed QLForm object
     * @throws IOException
     */
    public QLForm parseFile(String path) throws IOException {

        CharStream charStream = CharStreams.fromFileName(path);

        return parseCharstream(charStream);

    }

    /**
     * Reads a QL form from a string and returns a parsed QLForm object
     * @param s
     * @return A parsed QLForm object
     */
    public QLForm parseString(String s) {

        CharStream charStream = CharStreams.fromString(s);

        return parseCharstream(charStream);

    }

    /**
     * Reads a QL form from a Charstream and returns a parsed QLForm object
     * @param charStream
     * @return A parsed QLForm object
     */
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


            Expression expression = null;
            
            if(ctx.expression() != null)
                expression = expressionVisitor.visitExpression(ctx.expression());

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
            ArithmeticVisitor arithmeticVisitor = new ArithmeticVisitor();
            OperatorVisitor operatorVisitor = new OperatorVisitor();


            List<QLParser.ExpressionContext> expressions = ctx.expression();
            QLParser.TermContext termContext = ctx.term();
            TerminalNode notNode = ctx.NOT();
            QLParser.FactorContext factorContext = ctx.factor();
            QLParser.MuldivContext muldivContext = ctx.muldiv();
            QLParser.AddsubContext addsubContext = ctx.addsub();
            QLParser.OperatorContext operatorContext = ctx.operator();


            if(expressions.size() == 1 && notNode == null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)));

            if(termContext != null)
                return new Expression(termVisitor.visitTerm(ctx.term()));

            if(notNode != null)
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), new Not());

            if(factorContext != null) {
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitFactor(factorContext));
            }

            if(muldivContext != null) {
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitMuldiv(muldivContext));
            }

            if(addsubContext != null) {
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), arithmeticVisitor.visitAddsub(addsubContext));
            }

            if(operatorContext != null) {
                return new Expression(expressionVisitor.visitExpression(expressions.get(0)), expressionVisitor.visitExpression(expressions.get(1)), operatorVisitor.visitOperator(operatorContext));
            }

            return new Expression(); //TODO
        }
    }

    private static class TermVisitor extends QLBaseVisitor<Term>{

        @Override
        public Term visitTerm(@NotNull QLParser.TermContext ctx) {
            TerminalNode bool = ctx.BOOLEAN();
            TerminalNode qlstring = ctx.STRING();
            TerminalNode variable = ctx.VARIABLE();
            TerminalNode integer = ctx.INTEGER();
            TerminalNode decimal = ctx.DECIMAL();


            if(bool != null)
                return new Boolean(java.lang.Boolean.parseBoolean(bool.toString()));

            if(qlstring != null)
                return new QLString((String) qlstring.toString());

            if(variable != null)
                return new Variable((String) variable.toString());

            if(integer != null)
                System.out.println("Term is integer"); //TODO create Integer class

            if(decimal != null)
                return new Float((java.lang.Float.parseFloat(decimal.toString())));


            return new Boolean(true); //TODO return fitting value
        }

    }

    private static class ArithmeticVisitor extends QLBaseVisitor<Operator>{

        @Override
        public Operator visitFactor(@NotNull QLParser.FactorContext ctx) {
            return new Exponent(); //TODO
        }

        public Operator visitMuldiv(@NotNull QLParser.MuldivContext ctx) {
            return new Division(); //TODO
        }

        public Operator visitAddsub(@NotNull QLParser.AddsubContext ctx) {
            return new Addition(); //TODO
        }

    }

    private static class OperatorVisitor extends QLBaseVisitor<Operator>{

        @Override
        public Operator visitOperator(@NotNull QLParser.OperatorContext ctx) {
            return new Exponent(); //TODO return fitting operator
        }

    }
}