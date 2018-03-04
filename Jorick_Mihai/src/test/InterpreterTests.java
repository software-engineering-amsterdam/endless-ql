package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.interpreter.BooleanValue;
import org.uva.jomi.ql.interpreter.IntegerValue;
import org.uva.jomi.ql.interpreter.QLInterpreter;
import org.uva.jomi.ql.interpreter.StringValue;
import org.uva.jomi.ql.tests.utilities.TestUtilities;
import org.uva.jomi.ui.SymbolTable;

public class InterpreterTests {
	
	/*
	 * The question type should be ignored (the type resolver module in in charge if detecting type inconsistencies).
	 * The interpreter detects inconsistencies based on the operation type and values of the operands.  
	 */
	
	QLInterpreter interpreter = new QLInterpreter();
	
	// Addition tests.
	
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
	
	// Subtraction tests.
	
	String testSource4 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 2 - 1\n"
			+ "}";
	
	@Test
	public void test4() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource4);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(1));
	}
	
	String testSource5 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 1 - 2\n"
			+ "}";
	
	@Test
	public void test5() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource5);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(-1));
	}
	
	// Multiplication tests.
	
	String testSource6 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 1 * 2\n"
			+ "}";
	
	@Test
	public void test6() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource6);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(2));
	}
	
	String testSource7 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 2 * 4 - 1\n"
			+ "}";
	
	@Test
	public void test7() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource7);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(7));
	}
	
	// Division tests.
	
	String testSource8 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 4 / 2\n"
			+ "}";
	
	@Test
	public void test8() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource8);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(2));
	}
	
	String testSource9 =
			"form Form1 {\n"
			+ "\"question1\" q1: integer 2 / 4\n"
			+ "}";
	
	@Test
	public void test9() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource9);
		interpreter.interpret(ast);
		assertTrue(((IntegerValue) SymbolTable.getInstance().get("q1")).getValue().equals(0));
	}
	
	// And operation tests.
	
	String testSource10 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean true && true\n"
			+ "}";
	
	@Test
	public void test10() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource10);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource11 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean false && true\n"
			+ "}";
	
	@Test
	public void test11() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource11);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	String testSource12 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean true && false\n"
			+ "}";
	
	@Test
	public void test12() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource12);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	String testSource13 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean false && false\n"
			+ "}";
	
	@Test
	public void test13() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource13);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	// Or operation tests.
	
	String testSource14 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean true || true\n"
			+ "}";
	
	@Test
	public void test14() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource14);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource15 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean false || true\n"
			+ "}";
	
	@Test
	public void test15() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource15);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource16 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean true || false\n"
			+ "}";
	
	@Test
	public void test16() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource16);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource17 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean false || false\n"
			+ "}";
	
	@Test
	public void test17() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource17);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	// Less than operation tests.
	
	String testSource18 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 1 < 2\n"
			+ "}";
	
	@Test
	public void test18() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource18);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource19 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 < 1\n"
			+ "}";
	
	@Test
	public void test19() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource19);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	String testSource20 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 < 2\n"
			+ "}";
	
	@Test
	public void test20() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource20);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	// Less than or equal operation tests.
	
	String testSource21 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 1 <= 2\n"
			+ "}";
	
	@Test
	public void test21() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource21);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource22 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 <= 1\n"
			+ "}";
	
	@Test
	public void test22() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource22);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	String testSource23 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 <= 2\n"
			+ "}";
	
	@Test
	public void test23() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource23);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	// Greater than operation tests.
	
	String testSource24 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 1 > 2\n"
			+ "}";
	
	@Test
	public void test24() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource24);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	String testSource25 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 > 1\n"
			+ "}";
	
	@Test
	public void test25() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource25);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(true));
	}
	
	String testSource26 =
			"form Form1 {\n"
			+ "\"question1\" q1: boolean 2 > 2\n"
			+ "}";
	
	@Test
	public void test26() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource26);
		interpreter.interpret(ast);
		assertTrue(((BooleanValue) SymbolTable.getInstance().get("q1")).getValue().equals(false));
	}
	
	// Automatically generated negative tests.
	
	String generatedSource1 = "form Form1 {\"\" q0: integer 1 + true }";

	@Test
	public void generatedTest1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource1);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot add a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource2 = "form Form1 {\"\" q0: integer true + true }";

	@Test
	public void generatedTest2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource2);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot add a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource3 = "form Form1 {\"\" q0: integer true + \"string\" }";

	@Test
	public void generatedTest3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource3);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot add a BooleanValue and a StringValue"));
		}
	}

	String generatedSource4 = "form Form1 {\"\" q0: integer 1 - true }";

	@Test
	public void generatedTest4() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource4);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot subtract a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource5 = "form Form1 {\"\" q0: integer 1 - \"string\" }";

	@Test
	public void generatedTest5() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource5);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot subtract a IntegerValue and a StringValue"));
		}
	}

	String generatedSource6 = "form Form1 {\"\" q0: integer true - true }";

	@Test
	public void generatedTest6() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource6);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot subtract a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource7 = "form Form1 {\"\" q0: integer true - \"string\" }";

	@Test
	public void generatedTest7() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource7);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot subtract a BooleanValue and a StringValue"));
		}
	}

	String generatedSource8 = "form Form1 {\"\" q0: integer \"string\" - \"string\" }";

	@Test
	public void generatedTest8() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource8);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot subtract a StringValue and a StringValue"));
		}
	}

	String generatedSource9 = "form Form1 {\"\" q0: integer 1 * true }";

	@Test
	public void generatedTest9() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource9);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot multiply a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource10 = "form Form1 {\"\" q0: integer 1 * \"string\" }";

	@Test
	public void generatedTest10() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource10);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot multiply a IntegerValue and a StringValue"));
		}
	}

	String generatedSource11 = "form Form1 {\"\" q0: integer true * true }";

	@Test
	public void generatedTest11() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource11);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot multiply a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource12 = "form Form1 {\"\" q0: integer true * \"string\" }";

	@Test
	public void generatedTest12() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource12);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot multiply a BooleanValue and a StringValue"));
		}
	}

	String generatedSource13 = "form Form1 {\"\" q0: integer \"string\" * \"string\" }";

	@Test
	public void generatedTest13() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource13);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot multiply a StringValue and a StringValue"));
		}
	}

	String generatedSource14 = "form Form1 {\"\" q0: integer 1 / true }";

	@Test
	public void generatedTest14() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource14);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot divide a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource15 = "form Form1 {\"\" q0: integer 1 / \"string\" }";

	@Test
	public void generatedTest15() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource15);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot divide a IntegerValue and a StringValue"));
		}
	}

	String generatedSource16 = "form Form1 {\"\" q0: integer true / true }";

	@Test
	public void generatedTest16() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource16);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot divide a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource17 = "form Form1 {\"\" q0: integer true / \"string\" }";

	@Test
	public void generatedTest17() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource17);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot divide a BooleanValue and a StringValue"));
		}
	}

	String generatedSource18 = "form Form1 {\"\" q0: integer \"string\" / \"string\" }";

	@Test
	public void generatedTest18() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource18);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot divide a StringValue and a StringValue"));
		}
	}

	String generatedSource19 = "form Form1 {\"\" q0: integer 1 && 1 }";

	@Test
	public void generatedTest19() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource19);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot peform an And operation using a IntegerValue and a IntegerValue"));
		}
	}

	String generatedSource20 = "form Form1 {\"\" q0: integer 1 && true }";

	@Test
	public void generatedTest20() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource20);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot peform an And operation using a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource21 = "form Form1 {\"\" q0: integer 1 && \"string\" }";

	@Test
	public void generatedTest21() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource21);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot peform an And operation using a IntegerValue and a StringValue"));
		}
	}

	String generatedSource22 = "form Form1 {\"\" q0: integer true && \"string\" }";

	@Test
	public void generatedTest22() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource22);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot peform an And operation using a BooleanValue and a StringValue"));
		}
	}

	String generatedSource23 = "form Form1 {\"\" q0: integer \"string\" && \"string\" }";

	@Test
	public void generatedTest23() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource23);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot peform an And operation using a StringValue and a StringValue"));
		}
	}

	String generatedSource24 = "form Form1 {\"\" q0: integer 1 || 1 }";

	@Test
	public void generatedTest24() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource24);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot perform an Or operation using a IntegerValue and a IntegerValue"));
		}
	}

	String generatedSource25 = "form Form1 {\"\" q0: integer 1 || true }";

	@Test
	public void generatedTest25() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource25);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot perform an Or operation using a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource26 = "form Form1 {\"\" q0: integer 1 || \"string\" }";

	@Test
	public void generatedTest26() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource26);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot perform an Or operation using a IntegerValue and a StringValue"));
		}
	}

	String generatedSource27 = "form Form1 {\"\" q0: integer true || \"string\" }";

	@Test
	public void generatedTest27() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource27);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot perform an Or operation using a BooleanValue and a StringValue"));
		}
	}

	String generatedSource28 = "form Form1 {\"\" q0: integer \"string\" || \"string\" }";

	@Test
	public void generatedTest28() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource28);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot perform an Or operation using a StringValue and a StringValue"));
		}
	}

	String generatedSource29 = "form Form1 {\"\" q0: integer 1 > true }";

	@Test
	public void generatedTest29() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource29);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource30 = "form Form1 {\"\" q0: integer true > true }";

	@Test
	public void generatedTest30() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource30);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource31 = "form Form1 {\"\" q0: integer true > \"string\" }";

	@Test
	public void generatedTest31() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource31);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
		}
	}

//	String generatedSource32 = "form Form1 {\"\" q0: integer 1 >= true }";
//
//	@Test
//	public void generatedTest32() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource32);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
//		}
//	}
//
//	String generatedSource33 = "form Form1 {\"\" q0: integer true >= true }";
//
//	@Test
//	public void generatedTest33() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource33);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a BooleanValue"));
//		}
//	}
//
//	String generatedSource34 = "form Form1 {\"\" q0: integer true >= \"string\" }";
//
//	@Test
//	public void generatedTest34() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource34);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
//		}
//	}

	String generatedSource35 = "form Form1 {\"\" q0: integer 1 < true }";

	@Test
	public void generatedTest35() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource35);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource36 = "form Form1 {\"\" q0: integer true < true }";

	@Test
	public void generatedTest36() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource36);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource37 = "form Form1 {\"\" q0: integer true < \"string\" }";

	@Test
	public void generatedTest37() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource37);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
		}
	}

	String generatedSource38 = "form Form1 {\"\" q0: integer 1 <= true }";

	@Test
	public void generatedTest38() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource38);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
		}
	}

	String generatedSource39 = "form Form1 {\"\" q0: integer true <= true }";

	@Test
	public void generatedTest39() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource39);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a BooleanValue"));
		}
	}

	String generatedSource40 = "form Form1 {\"\" q0: integer true <= \"string\" }";

	@Test
	public void generatedTest40() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource40);
		try {
			interpreter.interpret(ast);
			fail("Test Failed");
		}

		catch (Exception e) {
			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
		}
	}

//	String generatedSource41 = "form Form1 {\"\" q0: integer 1 == true }";
//
//	@Test
//	public void generatedTest41() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource41);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
//		}
//	}
//
//	String generatedSource42 = "form Form1 {\"\" q0: integer 1 == \"string\" }";
//
//	@Test
//	public void generatedTest42() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource42);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a StringValue"));
//		}
//	}
//
//	String generatedSource43 = "form Form1 {\"\" q0: integer true == \"string\" }";
//
//	@Test
//	public void generatedTest43() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource43);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
//		}
//	}
//
//	String generatedSource44 = "form Form1 {\"\" q0: integer 1 != true }";
//
//	@Test
//	public void generatedTest44() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource44);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a BooleanValue"));
//		}
//	}
//
//	String generatedSource45 = "form Form1 {\"\" q0: integer 1 != \"string\" }";
//
//	@Test
//	public void generatedTest45() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource45);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a IntegerValue and a StringValue"));
//		}
//	}
//
//	String generatedSource46 = "form Form1 {\"\" q0: integer true != \"string\" }";
//
//	@Test
//	public void generatedTest46() throws Exception {
//		List<Stmt> ast = TestUtilities.buildAst(generatedSource46);
//		try {
//			interpreter.interpret(ast);
//			fail("Test Failed");
//		}
//
//		catch (Exception e) {
//			assertTrue(e.getMessage().equals("RuntimeError: Cannot compare a BooleanValue and a StringValue"));
//		}
//	}
}
