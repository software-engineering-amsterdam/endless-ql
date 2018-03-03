package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.DuplicatedLabelChecker;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class DuplicatedLabelTests {
	
DuplicatedLabelChecker labelChecker = new DuplicatedLabelChecker(false);
	
	String testSource1 =
			"form Form1 {\n"
			+ "\"question1\" q1: string\n"
			+ "\"question2\" q2: string\n"
			+ "\"question1\" q3: boolean\n"
			+ "if (true) {\n"
			+ "\"question2\" q4: integer"
			+ "} else {\n"
			+ "\"question1\" q5: integer"
			+ "}\n"
			+ "}";

	@Test
	public void test1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource1);
		labelChecker.check(ast);
		assertTrue(labelChecker.getNumberOfWarnings() == 3);
		assertTrue(labelChecker.
				getErrorAtIndex(0).equals("[DuplicatedLabelChecker] line: 4, column: 12: q3 has the same label as questions: q1"));
		assertTrue(labelChecker.
				getErrorAtIndex(1).equals("[DuplicatedLabelChecker] line: 6, column: 12: q4 has the same label as questions: q2"));
		assertTrue(labelChecker.
				getErrorAtIndex(2).equals("[DuplicatedLabelChecker] line: 7, column: 12: q5 has the same label as questions: q1 and q3"));
		
	}

}
