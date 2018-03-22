package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

class AstFormTests {

	String emptySource = "";
	@Test
	public void emptyFormTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(emptySource);
		assertEquals(ast.size(), 0);
	}
	
	String singleFormsSource = "form Form1 {}";
	@Test
	public void singleFormTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(singleFormsSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
	}
	
	String twoFormsSource = "form Form1 {} form Form2 {}";
	@Test
	public void twoFormsTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(twoFormsSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		FormStatement form2 = (FormStatement) ast.get(1);
		assertTrue(form2.getIdentifierName().equals("Form2"));
	}

}
