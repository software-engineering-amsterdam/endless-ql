package ql.ast.statements;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ql.ASTBuilder;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLVisitor;
import ql.ast.Form;
import ql.ast.Node;
import ql.ast.expressions.GroupExpression;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.binary.Addition;
import ql.ast.expressions.binary.Binary;
import ql.ast.expressions.literals.BooleanLiteral;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class CalculableQuestionTest {
    private final QLVisitor<Node> visitor = new ASTBuilder();
    private QLParser parser;

    private QLParser initQLParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }
    @Test
    public void testIsComputedQuestion() throws IOException {
        java.lang.String input = "form f1 { \"q1?\" value: money = (5 - 1) }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        assertThat(form.getStatements().get(0), instanceOf(CalculableQuestion.class));
    }

    @Test
    public void testGroupedExpression() throws IOException {
        java.lang.String input = "form f1 { \"q1?\" value: money = (2 - 1) }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        CalculableQuestion q1 = (CalculableQuestion) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Money.class));
        assertThat(q1.getCalculableValue(), instanceOf(GroupExpression.class));
    }

    @Test
    public void testBooleanExpression() throws IOException {
        java.lang.String input = "form q1 { \"q1?\" value: boolean = true == true }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        CalculableQuestion q1 = (CalculableQuestion) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Boolean.class));
        assertThat(q1.getCalculableValue(), instanceOf(Binary.class));
    }

    @Test
    public void testComputationExpression() throws IOException {
        java.lang.String input = "form f1 { \"q1?\" value: integer = 1+1 }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        CalculableQuestion q1 = (CalculableQuestion) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Integer.class));
        assertThat(q1.getCalculableValue(), instanceOf(Addition.class));
    }

    @Test
    public void testBooleanLiteral() throws IOException {
        java.lang.String input = "form f1 { \"q1?\" value: boolean = true }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        CalculableQuestion q1 = (CalculableQuestion) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Boolean.class));
        assertThat(q1.getCalculableValue(), instanceOf(BooleanLiteral.class));
    }

    @Test
    public void testIdentifier() throws IOException {
        java.lang.String input = "form f1 { \"q1?\" value: string = var }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        CalculableQuestion q1 = (CalculableQuestion) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.String.class));
        assertThat(q1.getCalculableValue(), instanceOf(Identifier.class));
    }
}