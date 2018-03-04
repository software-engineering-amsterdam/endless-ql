package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.interpreter.IntegerValue;
import org.uva.jomi.ql.interpreter.QLInterpreter;
import org.uva.jomi.ql.interpreter.StringValue;
import org.uva.jomi.ql.tests.utilities.TestUtilities;
import org.uva.jomi.ui.SymbolTable;

public class InterpreterTests {
	
	QLInterpreter interpreter = new QLInterpreter();
	
	String testSource1 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 1 + 2\n"
			+ "}";

	@Test
	public void test1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource1);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(3));
	}
	
	String testSource2 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 1\n"
			+ "\"question2\" q2: integer q1 + 2\n"
			+ "}";

	@Test
	public void test2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource2);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q2")).getValue().equals(3));
	}
	
	String testSource3 =
			"form Form1 {\n"
			+ "\"question1\" q1: string \"one\" + \" plus \" + \"two\"\n"
			+ "}";

	@Test
	public void test3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource3);
		interpreter.interpret(ast);
		assertTrue(((StringValue) SymbolTable.getInstance().get("q1")).getValue().equals("one plus two"));
		
	}
}
