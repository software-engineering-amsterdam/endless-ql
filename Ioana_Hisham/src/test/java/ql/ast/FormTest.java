package ql.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ql.ASTBuilder;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLVisitor;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FormTest {
    private final QLVisitor<Node> visitor = new ASTBuilder();
    @Test
    public void emptyForm() throws IOException {
        String input = "form f1 {}";
        String expected = "(form form (id f1) { })";

        QLParser parser = initQLParser(input);
        ParseTree tree = parser.form();

        assertEquals(expected, tree.toStringTree(parser));
    }

    @Test
    public void createFormIdentifier() {
        String input = "form f1 {}";
        String expected = "f1";

        QLParser parser = initQLParser(input);
        Form form = (Form)visitor.visit(parser.form());

        assertEquals(expected, form.getIdentifier().toString());
    }

    private QLParser initQLParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }
}