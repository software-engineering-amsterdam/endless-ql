package nl.uva.se.sc.niro;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class CompilerTest {

	@Test
	public void test() throws IOException {
		Compiler compiler = new Compiler();
		// Dirty little hack to get the fully qualified file name with the protocol
		String absoluteFileName = getClass().getResource("/simple.ql").toExternalForm().substring("file:".length());
		compiler.compileScriptFile(absoluteFileName);
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

}
