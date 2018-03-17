package tests.parsing.visitors;

import classes.Form;
import classes.Question;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;
import parsing.TreeBuilder;
import parsing.gen.QLLexer;
import parsing.gen.QLParser;
import parsing.visitors.InitVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InitVisitorTest {

   private TreeBuilder treeBuilder;
   private InitVisitor initVisitor;

    @Before
    public void init() throws Exception {
        initVisitor = new InitVisitor();
    }

    @Test
    public void visitForm() throws IOException {
        String expectation = "Box1HouseOwning";
        Form form = initVisitor.visitForm(new TreeBuilder().build(new FileInputStream("resources/exampleForm")));
        assertEquals(expectation, form.getId());
    }

    @Test
    public void visitBoolIdentifier() {
    }

    @Test
    public void visitBoolBraces() {
    }

    @Test
    public void visitNotOperation() {
    }

    @Test
    public void visitBoolOperation() {
    }

    @Test
    public void visitIfStatement() throws IOException {
        Object object = initVisitor.visitForm(new TreeBuilder().build(new FileInputStream("resources/exampleForm")));

    }
}
