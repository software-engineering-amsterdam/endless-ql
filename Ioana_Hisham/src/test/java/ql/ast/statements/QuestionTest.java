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
import ql.types.Money;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class QuestionTest {
    private final QLVisitor<Node> visitor = new ASTBuilder();
    private QLParser parser;

    private QLParser initQLParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }
    @Test
    public void testOneQuestion() throws IOException {
        String input = "form f1 { \"q1?\" value: boolean }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        Question q1 = (Question) questions.get(0);

        assertEquals("q1?", q1.getDescription().toString());
        assertEquals("value", q1.getIdentifier().toString());
        assertThat(q1.getType(), instanceOf(ql.types.Boolean.class));
    }

    @Test
    public void testIsQuestion() throws IOException {
        String input = "form f1 { \"q1?\" value: boolean }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        assertThat(form.getStatements().get(0), instanceOf(Question.class));
    }

    @Test
    public void testTwoQuestions() throws IOException {
        String input = "form f1 { \"q1?\" val1: boolean \"q2?\" val2: string }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        assertEquals("f1", form.getIdentifier().toString());
        assertEquals(2, questions.size());

        assertEquals("q1?", q1.getDescription().toString());
        assertEquals("val1", q1.getIdentifier().toString());
        assertThat(q1.getType(), instanceOf(ql.types.Boolean.class));

        assertEquals("q2?", q2.getDescription().toString());
        assertEquals("val2", q2.getIdentifier().toString());
        assertThat(q2.getType(), instanceOf(ql.types.String.class));
    }

    @Test
    public void testIntegerValueType() throws IOException {
        String input = "form f1 { \"q1?\" value: integer }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        Question q1 = (Question) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Integer.class));
    }


    @Test
    public void testMoneyValueType() throws IOException {
        String input = "form taxOfficeExample { \"q1?\" value: money }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        Question q1 = (Question) questions.get(0);

        assertThat(q1.getType(), instanceOf(ql.types.Money.class));
    }

    @Test
    public void testTwoLineNumbers() throws IOException {
        String input = "form q1 { \n\"q1?\" val1: boolean \n\"q2?\" val2: string }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> questions = form.getStatements();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);

        assertEquals(1, form.getLineNumber());
        assertEquals(2, q1.getLineNumber());
        assertEquals(3, q2.getLineNumber());
    }
}