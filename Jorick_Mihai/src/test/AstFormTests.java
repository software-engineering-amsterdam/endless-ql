package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

class AstFormTests {

	String emptySource = "";
	@Test
	public void emptyFormTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(emptySource);
		assertEquals(ast.size(), 0);
	}
	
	String singleFormsSource = "form Form1 {}";
	@Test
	public void singleFormTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(singleFormsSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
	}
	
	String twoFormsSource = "form Form1 {} form Form2 {}";
	@Test
	public void twoFormsTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(twoFormsSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		FormStmt form2 = (FormStmt) ast.get(1);
		assertTrue(form2.getIdentifierName().equals("Form2"));
	}

}
