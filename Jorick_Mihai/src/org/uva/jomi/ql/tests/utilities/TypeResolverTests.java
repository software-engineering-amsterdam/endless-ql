package org.uva.jomi.ql.tests.utilities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;

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
		assertTrue(typeResolver.getNumberOfErrors() == 13);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 15: Type mismatch, expected BOOLEAN, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(1).equals("[TypeResolver] line: 3, column: 14: Type mismatch, expected STRING, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(2).equals("[TypeResolver] line: 3, column: 18: Type mismatch, expected STRING, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(3).equals("[TypeResolver] line: 4, column: 12: Type mismatch, expected DATE, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(4).equals("[TypeResolver] line: 4, column: 16: Type mismatch, expected DATE, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(5).equals("[TypeResolver] line: 5, column: 13: Type mismatch, expected MONEY, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(6).equals("[TypeResolver] line: 5, column: 17: Type mismatch, expected MONEY, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(7).equals("[TypeResolver] line: 6, column: 15: Type mismatch, expected DECIMAL, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(8).equals("[TypeResolver] line: 6, column: 19: Type mismatch, expected DECIMAL, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(9).equals("[TypeResolver] line: 7, column: 15: Type mismatch, expected DECIMAL, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(10).equals("[TypeResolver] line: 7, column: 20: Type mismatch, expected DECIMAL, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(11).equals("[TypeResolver] line: 8, column: 15: Type mismatch, expected DECIMAL, but got INTEGER"));
		assertTrue(typeResolver.
				getErrorAtIndex(12).equals("[TypeResolver] line: 8, column: 20: Type mismatch, expected DECIMAL, but got INTEGER"));
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
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 23: Type mismatch, expected BOOLEAN, but got STRING"));
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
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 27: Type mismatch, expected INTEGER, but got STRING"));
	}
	
	
	String testSource4 =
			"form Form1 {\n"
			+ "\"\" q1: integer 1\n"
			+ "\"\" q2: boolean 1 + q1\n"
			+ "}";

	@Test
	public void test4() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource4);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 3, column: 15: Type mismatch, expected BOOLEAN, but got INTEGER"));
	}
}
