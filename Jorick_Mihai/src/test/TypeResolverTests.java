package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class TypeResolverTests {
	
	TypeResolver typeResolver = new TypeResolver(false);
	
	String testSource1 =
			"form Form1 {\n"
			+ "\"\" q1: boolean 1\n"
			+ "\"\" q2: string 1 + 1\n"
			+ "\"\" q3: date 1 - 1\n"
			+ "\"\" q4: money 1 * 1\n"
			+ "\"\" q5: decimal 1 / 1\n"
			+ "\"\" q6: decimal 1 || 1\n"
			+ "\"\" q7: decimal 1 && 1\n"
			+ "}";

	@Test
	public void test1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource1);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 7);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 15: Type mismatch, expected boolean, but got integer"));
		assertTrue(typeResolver.
				getErrorAtIndex(1).equals("[TypeResolver] line: 3, column: 14: Type mismatch, expected string, but got integer"));
		assertTrue(typeResolver.
				getErrorAtIndex(2).equals("[TypeResolver] line: 4, column: 12: Type mismatch, expected date, but got integer"));
		assertTrue(typeResolver.
				getErrorAtIndex(3).equals("[TypeResolver] line: 5, column: 13: Type mismatch, expected money, but got integer"));
		assertTrue(typeResolver.
				getErrorAtIndex(4).equals("[TypeResolver] line: 6, column: 15: Type mismatch, expected decimal, but got integer"));
		assertTrue(typeResolver.
				getErrorAtIndex(5).equals("[TypeResolver] line: 7, column: 15: Type mismatch, desired type: integer, allowed types: boolean"));
	}
	
	String testSource2 =
			"form Form1 {\n"
			+ "\"\" q1: boolean true && \"string\" \n"
			+ "}";

	@Test
	public void test2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource2);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 20: Operator && is undefined for types: boolean and string"));
	}
	
	String testSource3 =
			"form Form1 {\n"
			+ "\"\" q1: integer 1 + 2 + 3 + \"string\"\n"
			+ "}";

	@Test
	public void test3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource3);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 25: Operator + is undefined for types: integer and string"));
	}
	
	
//	String testSource4 =
//			"form Form1 {\n"
//			+ "\"\" q1: integer 1\n"
//			+ "\"\" q2: boolean 1 + q1\n"
//			+ "}";
//
//	@Test
//	public void test4() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(testSource4);
//		typeResolver.resolve(ast);
//		assertTrue(typeResolver.getNumberOfErrors() == 1);
//		assertTrue(typeResolver.
//				getErrorAtIndex(0).equals("[TypeResolver] line: 3, column: 15: Type mismatch, expected BOOLEAN, but got INTEGER"));
//	}
}
