package nl.uva.se.sc.niro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class VisitingCompilerTest {
	@Before
	public void clearErrors() {
		ErrorListener.errorReported = false;
	}
	
	@Test
	public void simpleQuestionaireTest() throws IOException {
		VisitingCompiler compiler = new VisitingCompiler();
		compiler.compileScriptFile(toCharStream("/simple.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void conditionalIfQuestionaireTest() throws IOException {
		VisitingCompiler compiler = new VisitingCompiler();
		compiler.compileScriptFile(toCharStream("/simple-conditional-if.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

    @Test
    public void conditionalIfElseQuestionaireTest() throws IOException {
        VisitingCompiler compiler = new VisitingCompiler();
        compiler.compileScriptFile(toCharStream("/simple-conditional-if-else.ql"));
        if (ErrorListener.errorReported) {
            fail("Somehting went wrong, see the console for more information!");
        }
    }

	@Test
	public void nestedConditionalQuestionaireTest() throws IOException {
		VisitingCompiler compiler = new VisitingCompiler();
		compiler.compileScriptFile(toCharStream("/nested-conditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void expressionConditionalIfQuestionaireTest() throws IOException {
		VisitingCompiler compiler = new VisitingCompiler();
		compiler.compileScriptFile(toCharStream("/expression-conditional-if.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

    @Test
    public void expressionConditionalIfElseQuestionaireTest() throws IOException {
        VisitingCompiler compiler = new VisitingCompiler();
        compiler.compileScriptFile(toCharStream("/expression-conditional-if-else.ql"));
        if (ErrorListener.errorReported) {
            fail("Somehting went wrong, see the console for more information!");
        }
    }

    @Ignore
	@Test
	public void nestedExpressionQuestionaireTest() throws IOException {
		VisitingCompiler compiler = new VisitingCompiler();
		compiler.compileScriptFile(toCharStream("/nested-expression.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	private CharStream toCharStream(String fileName) throws IOException {
		return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
	}
	
}
