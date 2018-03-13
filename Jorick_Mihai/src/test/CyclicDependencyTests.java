package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.CyclicDependencyChecker;
import org.uva.jomi.ql.ast.analysis.IdentifierMapBuilder;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class CyclicDependencyTests {

	CyclicDependencyChecker checker = new CyclicDependencyChecker(false);
	IdentifierMapBuilder identifierMapBuilder = new IdentifierMapBuilder();

	String test1 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean\n"
			+ "}";

	@Test
	public void test1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(test1);
		// No cycles
		assertFalse(checker.check(identifierMapBuilder.buildMap(ast)));
		assertTrue(checker.getErrors().size() == 0);
	}
	
	String test2 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean q1\n"
			+ "}";

	@Test
	public void test2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(test2);
		// No cycles
		assertTrue(checker.check(identifierMapBuilder.buildMap(ast)));
		assertTrue(checker.getErrors().size() == 1);
		assertTrue(checker.
				getErrorAtIndex(0).equals("[CyclicDependencyChecker] A cyclic dependency was found between the following questions: q1"));
	}
	
	String test3 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean q2\n"
			+ "\"question2\" q2: boolean q1\n"
			+ "}";

	@Test
	public void test3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(test3);
		// No cycles
		assertTrue(checker.check(identifierMapBuilder.buildMap(ast)));
		assertTrue(checker.getErrors().size() == 1);
		assertTrue(checker.
				getErrorAtIndex(0).equals("[CyclicDependencyChecker] A cyclic dependency was found between the following questions: q1, q2"));
	}
}
