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

public class IfThenElseTest {
    private final QLVisitor<Node> visitor = new ASTBuilder();
    private QLParser parser;

    private QLParser initQLParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }

    @Test
    public void testIsIfThenElse() throws IOException {
        String input = "form f1 { if (hasSoldHouse) { \"q1?\" val1: boolean } else { \"q2?\" val2: boolean } }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        assertThat(form.getStatements().get(0), instanceOf(IfThenElse.class));
    }

    @Test
    public void testIfElseBlock() throws IOException {
        String input = "form f1 { if (hasSoldHouse) {  } else { \"q2?\" val2: boolean } }";

        parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        List<Statement> statements = form.getStatements();
        IfThenElse ifThenElse = (IfThenElse) statements.get(0);

        assertEquals(1, ifThenElse.getThenStatements().size());
    }
}