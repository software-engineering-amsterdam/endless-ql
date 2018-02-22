package org.uva.jomi.ql.tests.utilities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.Stmt;

public class AstTests {
	String firstTestSource = "form Form1 {}";

	@Test
	public void firstTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(firstTestSource);
		FormStmt Form1 = (FormStmt) ast.get(0);
		//assertTrue(condition);
		//Form1.identifier.token.getLexeme() == "";
	}
}
