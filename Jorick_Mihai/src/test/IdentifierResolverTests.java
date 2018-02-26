package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class IdentifierResolverTests {
	
	IdentifierResolver identifierResolver = new IdentifierResolver(false);
	
	String questionNameSelfRefereceTest =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean q1\n"
			+ "}";

	@Test
	public void questionNameSelfRefereceTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(questionNameSelfRefereceTest);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 2, column: 24: Undeclared identifier: q1"));
	}
	
	String undefinedQuestionName1 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean q2\n"
			+ "}";
	
	@Test
	public void undeclaredQuestionName1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(undefinedQuestionName1);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 2, column: 24: Undeclared identifier: q2"));
	}
	
	String undefinedQuestionName2 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean\n"
			+ "if (q2) {}\n"
			+ "}";
	
	@Test
	public void undeclaredQuestionName2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(undefinedQuestionName2);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 3, column: 4: Undeclared identifier: q2"));
	}
	
	String readOnlyQuestionName1 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean\n"
			+ "if (q1) {\n"
			+ "\"question2\" q1: boolean q1\n"
			+ "}\n"
			+ "}";
	
	@Test
	public void readOnlyQuestionName1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(readOnlyQuestionName1);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 4, column: 12: Read-only identifier already declared in an outside scope: q1"));
	}
	
	String readOnlyQuestionName2 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean\n"
			+ "\"question1\" q1: boolean\n"
			+ "}";
	
	@Test
	public void readOnlyQuestionName2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(readOnlyQuestionName2);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 3, column: 12: Read-only identifier already declared the current scope: q1"));
	}
	
	String readOnlyQuestionName3 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean\n"
			+ "if (q1) {\n"
			+ "} else {\n"
			+ "\"question2\" q1: boolean q1\n"
			+ "}\n"
			+ "}";
	
	@Test
	public void readOnlyQuestionName3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(readOnlyQuestionName3);
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 1);
		assertTrue(identifierResolver.
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 5, column: 12: Read-only identifier already declared in an outside scope: q1"));
	}
}
