package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

class AstQuestionTypeTests {

	String typeStringSource = "form Form1 { \"Question 1\" question1: string }";	
	@Test
	public void typeStringTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeStringSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "string");
	}
	
	String typeBooleanSource = "form Form1 { \"Question 1\" question1: boolean }";	
	@Test
	public void typeBooleanTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeBooleanSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "boolean");
	}
	
	String typeIntegerSource = "form Form1 { \"Question 1\" question1: integer }";	
	@Test
	public void typeIntegerTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeIntegerSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "integer");
	}
	
	String typeDecimalSource = "form Form1 { \"Question 1\" question1: decimal }";	
	@Test
	public void typeDecimalTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeDecimalSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "decimal");
	}
	
	String typeDateSource = "form Form1 { \"Question 1\" question1: date }";	
	@Test
	public void typeDateTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeDateSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "date");
	}
	
	String typeMoneySource = "form Form1 { \"Question 1\" question1: money }";	
	@Test
	public void typeMoneyTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(typeMoneySource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertEquals(question1.getType().getName(), "money");
	}
	
	String typeUnkownSource = "form Form1 { \"Question 1\" question1: unkown }";
	@Test
	public void typeUnkownTest() {
		List<Stmt> ast = TestUtilities.buildAst(typeUnkownSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		
		QuestionStmt question1 = (QuestionStmt) form1.blockStmt.statements.get(0);
		assertNull(question1.getType());
	}

}