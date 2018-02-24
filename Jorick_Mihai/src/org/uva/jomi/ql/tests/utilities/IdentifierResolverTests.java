package org.uva.jomi.ql.tests.utilities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.statements.Stmt;

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
				getErrorAtIndex(0).equals("[IdentifierResolver] line: 2, column: 24: Undefined identifier"));
	}
}
