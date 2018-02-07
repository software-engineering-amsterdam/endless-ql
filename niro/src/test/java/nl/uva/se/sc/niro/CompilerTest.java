package nl.uva.se.sc.niro;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class CompilerTest {
	@Before
	public void clearErrors() {
		ErrorListener.errorReported = false;
	}
	
	@Test
	public void simpleQuestionaireTest() throws IOException {
		Compiler compiler = new Compiler();
		compiler.compileScriptFile(convertToAbsolute("/simple.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void conditionalQuestionaireTest() throws IOException {
		Compiler compiler = new Compiler();
		compiler.compileScriptFile(convertToAbsolute("/conditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void nestedConditionalQuestionaireTest() throws IOException {
		Compiler compiler = new Compiler();
		compiler.compileScriptFile(convertToAbsolute("/nestedConditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	private String convertToAbsolute(String fileName) {
		URL absoluteURL = getClass().getResource(fileName);
		return absoluteURL.toExternalForm().substring(absoluteURL.getProtocol().length() + 1);
	}
}
