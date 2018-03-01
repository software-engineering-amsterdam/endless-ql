package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class AstCommandTests {
	
	String singleQuestionSource = "form Form1 { \"Question 1\" question1: string }";	
	@Test
	public void singleQuestionTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(singleQuestionSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		QuestionStmt question1 = (QuestionStmt) form1.getBlockStmt().getStmtAtIndex(0);
		assertEquals(question1.label, "\"Question 1\"");
		assertEquals(question1.identifier.getName(), "question1");
	}
	
	String twoQuestionsSource = "form Form1 { \"Question 1\" question1: string \"Question 2\" question2: string }";	
	@Test
	public void twoQuestionsTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(twoQuestionsSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		QuestionStmt question1 = (QuestionStmt) form1.getBlockStmt().getStmtAtIndex(0);
		assertEquals(question1.label, "\"Question 1\"");
		assertEquals(question1.identifier.getName(), "question1");
		
		QuestionStmt question2 = (QuestionStmt) form1.getBlockStmt().getStmtAtIndex(1);
		assertEquals(question2.label, "\"Question 2\"");
		assertEquals(question2.identifier.getName(), "question2");
	}
	
	String singleIfSource = "form Form1 { if(true) { \"Question 1\" question1: string } }";	
	@Test
	public void singleIfTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(singleIfSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfStmt ifStmt = (IfStmt) form1.getBlockStmt().getStmtAtIndex(0);
		assertNotNull(ifStmt);
		
		QuestionStmt question1 = (QuestionStmt) ifStmt.blockStmt.getStmtAtIndex(0);
		assertEquals(question1.label, "\"Question 1\"");
		assertEquals(question1.identifier.getName(), "question1");
	}
	
	String twoIfSource = "form Form1 { if(true) { \"Question 1\" question1: string } \"Question 2\" question2: string }";	
	@Test
	public void twoIfTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(twoIfSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfStmt ifStmt = (IfStmt) form1.getBlockStmt().getStmtAtIndex(0);
		assertNotNull(ifStmt);
		
		QuestionStmt question1 = (QuestionStmt) ifStmt.blockStmt.getStmtAtIndex(0);
		assertEquals(question1.label, "\"Question 1\"");
		assertEquals(question1.identifier.getName(), "question1");
		
		QuestionStmt question2 = (QuestionStmt) form1.getBlockStmt().getStmtAtIndex(1);
		assertEquals(question2.label, "\"Question 2\"");
		assertEquals(question2.identifier.getName(), "question2");
	}
	
	String ifElseSource = "form Form1 { if(true) { \"Question 1\" question1: string } else { \"Question 2\" question2: string } }";	
	@Test
	public void ifElseTest() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(ifElseSource);
		FormStmt form1 = (FormStmt) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfElseStmt ifElseStmt = (IfElseStmt) form1.getBlockStmt().getStmtAtIndex(0);
		assertNotNull(ifElseStmt);
		
		QuestionStmt question1 = (QuestionStmt) ifElseStmt.ifBlockStmt.getStmtAtIndex(0);
		assertEquals(question1.label, "\"Question 1\"");
		assertEquals(question1.identifier.getName(), "question1");
		
		QuestionStmt question2 = (QuestionStmt) ifElseStmt.elseBlockStmt.getStmtAtIndex(0);
		assertEquals(question2.label, "\"Question 2\"");
		assertEquals(question2.identifier.getName(), "question2");
	}

}
