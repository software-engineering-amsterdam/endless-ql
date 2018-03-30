package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

class AstQuestionTypeTests {

	String typeStringSource = "form Form1 { \"Question 1\" question1: string }";
	@Test
	public void typeStringTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeStringSource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "string");
	}

	String typeBooleanSource = "form Form1 { \"Question 1\" question1: boolean }";
	@Test
	public void typeBooleanTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeBooleanSource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "boolean");
	}

	String typeIntegerSource = "form Form1 { \"Question 1\" question1: integer }";
	@Test
	public void typeIntegerTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeIntegerSource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "integer");
	}

	String typeDecimalSource = "form Form1 { \"Question 1\" question1: decimal }";
	@Test
	public void typeDecimalTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeDecimalSource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "decimal");
	}

	String typeDateSource = "form Form1 { \"Question 1\" question1: date }";
	@Test
	public void typeDateTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeDateSource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "date");
	}

	String typeMoneySource = "form Form1 { \"Question 1\" question1: money }";
	@Test
	public void typeMoneyTest() throws Exception {
		List<Statement> ast = TestUtilities.buildAst(typeMoneySource);
		FormStatement form1 = (FormStatement) ast.get(0);

		QuestionStatement question1 = (QuestionStatement) form1.getBlockStmt().getStatementAtIndex(0);
		assertEquals(question1.getType().getName(), "money");
	}

	String typeUnkownSource = "form Form1 { \"Question 1\" question1: unkown }";
	@Test
	public void typeUnkownTest() {
		assertNull(TestUtilities.buildAst(typeUnkownSource));
	}

}