package com.chariotit.uva.sc.qdsl.grammar;

import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.parser.GLVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

public class GLGrammarTest {

    private QLParser getParser(String inputFile) {
        try {
            String testDataDir = "src/test/data/grammar/";
            QLLexer lexer = new QLLexer(CharStreams.fromFileName(testDataDir + inputFile));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            return new QLParser(tokens);
        } catch (IOException e) {
            throw new RuntimeException("Could not find input file");
        }
    }

    private AstRoot getAst(String inputFile) {
        QLParser parser = getParser(inputFile);
        ParseTree tree = parser.forms();
        GLVisitor visitor = new GLVisitor();

        return (AstRoot)visitor.visit(tree);
    }

    @Test
    public void testForms() {
        AstRoot astRoot = getAst("forms.ql");

        assertEquals(astRoot.getForms().size(), 2);

        Form firstForm = astRoot.getForms().get(0);
        LineElement firstElement = (LineElement)firstForm.getFormElements().get(0);

        assertEquals(firstElement.getLabel().getLabel(), "hasSoldHouse");

        IfBlock ifBlock = (IfBlock)firstForm.getFormElements().get(3);
        assertEquals(ifBlock.getFormElements().size(), 3);
        assertEquals(((LabelExpression)(ifBlock.getExpression())).getLabel().getLabel(),
                "hasSoldHouse");
    }
}
