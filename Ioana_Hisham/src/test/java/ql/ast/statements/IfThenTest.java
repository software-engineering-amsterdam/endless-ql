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

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class IfThenTest {
    private final QLVisitor<Node> visitor = new ASTBuilder();
    private QLParser parser;

    private QLParser initQLParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }

    @Test
    public void testIf() throws IOException {
        String input = "form f1 { if (hasSoldHouse) { \"q1?\" val1: boolean } }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> statements = form.getStatements();
        IfThen ifThen = (IfThen) statements.get(0);

        assertEquals(1, ifThen.getThenStatements().size());
    }

    @Test
    public void testMultipleIfBlocks() throws IOException {
        String input = "form f1 { if (hasSoldHouse) { \"q1?\" val1: boolean } if (bool) { \"q2?\" val2: boolean } }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> statements = form.getStatements();
        IfThen ifThen1 = (IfThen) statements.get(0);
        IfThen ifThen2 = (IfThen) statements.get(0);

        assertEquals(2, statements.size());
        assertEquals(1, ifThen1.getThenStatements().size());
        assertEquals(1, ifThen2.getThenStatements().size());
    }

    @Test
    public void testQuestionAndIfThen() throws IOException {
        String input = "form f1 { \"q1?\" val1: boolean if (hasSoldHouse) { \"q2?\" val2: boolean } }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> statements = form.getStatements();

        assertEquals(2, statements.size());
        assertThat(statements.get(0), instanceOf(Question.class));
        assertThat(statements.get(1), instanceOf(IfThen.class));
    }
}