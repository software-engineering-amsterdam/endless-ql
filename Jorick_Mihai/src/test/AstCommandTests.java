package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.IfElseStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class AstCommandTests {
	
	String singleQuestionSource = "form Form1 { \"Question 1\" question1: string }";	
	@Test
	public void singleQuestionTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(singleQuestionSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getLabel(), "\"Question 1\"");
		assertEquals(question1.getName(), "question1");
	}
	
	String twoQuestionsSource = "form Form1 { \"Question 1\" question1: string \"Question 2\" question2: string }";	
	@Test
	public void twoQuestionsTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(twoQuestionsSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getLabel(), "\"Question 1\"");
		assertEquals(question1.getName(), "question1");
		
		QuestionStatement question2 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(1);
		assertEquals(question2.getLabel(), "\"Question 2\"");
		assertEquals(question2.getName(), "question2");
	}
	
	String singleIfSource = "form Form1 { if(true) { \"Question 1\" question1: string } }";	
	@Test
	public void singleIfTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(singleIfSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfStatement ifStmt = (IfStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertNotNull(ifStmt);
		
		QuestionStatement question1 = (QuestionStatement) ifStmt.getIfBlockStatement().getStatementAtIndex(0);
		assertEquals(question1.getLabel(), "\"Question 1\"");
		assertEquals(question1.getName(), "question1");
	}
	
	String twoIfSource = "form Form1 { if(true) { \"Question 1\" question1: string } \"Question 2\" question2: string }";	
	@Test
	public void twoIfTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(twoIfSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfStatement ifStmt = (IfStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertNotNull(ifStmt);
		
		QuestionStatement question1 = (QuestionStatement) ifStmt.getIfBlockStatement().getStatementAtIndex(0);
		assertEquals(question1.getLabel(), "\"Question 1\"");
		assertEquals(question1.getName(), "question1");
		
		QuestionStatement question2 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(1);
		assertEquals(question2.getLabel(), "\"Question 2\"");
		assertEquals(question2.getName(), "question2");
	}
	
	String ifElseSource = "form Form1 { if(true) { \"Question 1\" question1: string } else { \"Question 2\" question2: string } }";	
	@Test
	public void ifElseTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(ifElseSource);
		FormStatement form1 = (FormStatement) ast.get(0);
		assertTrue(form1.getIdentifierName().equals("Form1"));
		
		IfElseStatement ifElseStmt = (IfElseStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertNotNull(ifElseStmt);
		
		QuestionStatement question1 = (QuestionStatement) ifElseStmt.getIfBlockStatement().getStatementAtIndex(0);
		assertEquals(question1.getLabel(), "\"Question 1\"");
		assertEquals(question1.getName(), "question1");
		
		QuestionStatement question2 = (QuestionStatement) ifElseStmt.getElseBlockStatement().getStatementAtIndex(0);
		assertEquals(question2.getLabel(), "\"Question 2\"");
		assertEquals(question2.getName(), "question2");
	}

}
