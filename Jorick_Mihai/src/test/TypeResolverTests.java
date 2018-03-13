package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.tests.utilities.TestUtilities;

public class TypeResolverTests {

	TypeResolver typeResolver = new TypeResolver(false);
	IdentifierResolver identifierResolver = new IdentifierResolver(false);

	String testSource1 =
			"form Form1 {\n"
			+ "\"\" q1: boolean true && \"string\" \n"
			+ "}";

	@Test
	public void test1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource1);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 20: Operator && is undefined for types: boolean and string"));
	}

	String testSource2 =
			"form Form1 {\n"
			+ "\"\" q1: integer 1 + 2 + 3 + \"string\"\n"
			+ "}";

	@Test
	public void test2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource2);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 25: Operator + is undefined for types: integer and string"));
		assertTrue(typeResolver.
				getErrorAtIndex(1).equals("[TypeResolver] line: 2, column: 15: Type mismatch, expected integer, but got unknown type"));
	}


	String testSource3 =
			"form Form1 {\n"
			+ "\"\" q1: integer 1\n"
			+ "\"\" q2: boolean 1 + q1\n"
			+ "}";

	@Test
	public void test3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource3);
		// The identifier resolver is needed when in order to get the types of the identifiers.
		identifierResolver.resolve(ast);
		assertTrue(identifierResolver.getNumberOfErrors() == 0);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
				getErrorAtIndex(0).equals("[TypeResolver] line: 3, column: 15: Type mismatch, expected boolean, but got integer"));
	}


	String testSource4 =
			"form Form1 {\n"
			+ "\"\" q1: integer (1 + 2) - 3\n"
			+ "}";

	@Test
	public void test4() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource4);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String testSource5 =
			"form Form1 {\n"
			+ "\"\" q1: integer !(1 + 2) - 3\n"
			+ "}";

	@Test
	public void test5() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource5);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 16: Type mismatch, requested type: integer, allowed types: boolean"));
	}

	String testSource6 =
			"form Form1 {\n"
			+ "\"\" q1: boolean !true\n"
			+ "}";

	@Test
	public void test6() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource6);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String testSource7 =
			"form Form1 {\n"
			+ "if (1) {\n"
			+ "}\n"
			+ "}";

	@Test
	public void test7() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource7);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
		getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 4: Type mismatch, expected boolean, but got integer"));
	}

	String testSource8 =
			"form Form1 {\n"
			+ "if (\"true\") {\n"
			+ "}\n"
			+ "}";

	@Test
	public void test8() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource8);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
		getErrorAtIndex(0).equals("[TypeResolver] line: 2, column: 4: Type mismatch, expected boolean, but got string"));
	}

	String testSource9 =
			"form Form1 {\n"
			+ "if (1 == 7) {\n"
			+ "}\n"
			+ "}";

	@Test
	public void test9() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(testSource9);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	/*
	 *  The test bellow have been generated automatically.
	 */

	String generatedSource1 = "form Form1 {\"\" q0: string 1 + 1}";

	@Test
	public void generatedTest1() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource1);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got integer"));
	}

	String generatedSource2 = "form Form1 {\"\" q0: string 1 + true}";

	@Test
	public void generatedTest2() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource2);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator + is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource3 = "form Form1 {\"\" q0: string 1 + \"string\"}";

	@Test
	public void generatedTest3() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource3);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator + is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource4 = "form Form1 {\"\" q0: string true + true}";

	@Test
	public void generatedTest4() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource4);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource5 = "form Form1 {\"\" q0: string true + \"string\"}";

	@Test
	public void generatedTest5() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource5);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator + is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource6 = "form Form1 {\"\" q0: string \"string\" + \"string\"}";

	@Test
	public void generatedTest6() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource6);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource7 = "form Form1 {\"\" q0: string 1 - 1}";

	@Test
	public void generatedTest7() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource7);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got integer"));
	}

	String generatedSource8 = "form Form1 {\"\" q0: string 1 - true}";

	@Test
	public void generatedTest8() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource8);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator - is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource9 = "form Form1 {\"\" q0: string 1 - \"string\"}";

	@Test
	public void generatedTest9() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource9);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator - is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource10 = "form Form1 {\"\" q0: string true - true}";

	@Test
	public void generatedTest10() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource10);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource11 = "form Form1 {\"\" q0: string true - \"string\"}";

	@Test
	public void generatedTest11() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource11);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator - is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource12 = "form Form1 {\"\" q0: string \"string\" - \"string\"}";

	@Test
	public void generatedTest12() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource12);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource13 = "form Form1 {\"\" q0: string 1 * 1}";

	@Test
	public void generatedTest13() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource13);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got integer"));
	}

	String generatedSource14 = "form Form1 {\"\" q0: string 1 * true}";

	@Test
	public void generatedTest14() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource14);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator * is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource15 = "form Form1 {\"\" q0: string 1 * \"string\"}";

	@Test
	public void generatedTest15() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource15);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator * is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource16 = "form Form1 {\"\" q0: string true * true}";

	@Test
	public void generatedTest16() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource16);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource17 = "form Form1 {\"\" q0: string true * \"string\"}";

	@Test
	public void generatedTest17() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource17);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator * is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource18 = "form Form1 {\"\" q0: string \"string\" * \"string\"}";

	@Test
	public void generatedTest18() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource18);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource19 = "form Form1 {\"\" q0: string 1 / 1}";

	@Test
	public void generatedTest19() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource19);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got integer"));
	}

	String generatedSource20 = "form Form1 {\"\" q0: string 1 / true}";

	@Test
	public void generatedTest20() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource20);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator / is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource21 = "form Form1 {\"\" q0: string 1 / \"string\"}";

	@Test
	public void generatedTest21() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource21);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator / is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource22 = "form Form1 {\"\" q0: string true / true}";

	@Test
	public void generatedTest22() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource22);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource23 = "form Form1 {\"\" q0: string true / \"string\"}";

	@Test
	public void generatedTest23() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource23);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator / is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource24 = "form Form1 {\"\" q0: string \"string\" / \"string\"}";

	@Test
	public void generatedTest24() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource24);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got unknown type"));
	}

	String generatedSource25 = "form Form1 {\"\" q0: string 1 && 1}";

	@Test
	public void generatedTest25() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource25);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: integer, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource26 = "form Form1 {\"\" q0: string 1 && true}";

	@Test
	public void generatedTest26() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource26);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator && is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource27 = "form Form1 {\"\" q0: string 1 && \"string\"}";

	@Test
	public void generatedTest27() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource27);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator && is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource28 = "form Form1 {\"\" q0: string true && true}";

	@Test
	public void generatedTest28() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource28);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource29 = "form Form1 {\"\" q0: string true && \"string\"}";

	@Test
	public void generatedTest29() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource29);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator && is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource30 = "form Form1 {\"\" q0: string \"string\" && \"string\"}";

	@Test
	public void generatedTest30() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource30);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: string, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource31 = "form Form1 {\"\" q0: string 1 || 1}";

	@Test
	public void generatedTest31() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource31);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: integer, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource32 = "form Form1 {\"\" q0: string 1 || true}";

	@Test
	public void generatedTest32() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource32);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator || is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource33 = "form Form1 {\"\" q0: string 1 || \"string\"}";

	@Test
	public void generatedTest33() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource33);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator || is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource34 = "form Form1 {\"\" q0: string true || true}";

	@Test
	public void generatedTest34() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource34);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource35 = "form Form1 {\"\" q0: string true || \"string\"}";

	@Test
	public void generatedTest35() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource35);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator || is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource36 = "form Form1 {\"\" q0: string \"string\" || \"string\"}";

	@Test
	public void generatedTest36() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource36);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: string, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource37 = "form Form1 {\"\" q0: string 1 > 1}";

	@Test
	public void generatedTest37() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource37);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource38 = "form Form1 {\"\" q0: string 1 > true}";

	@Test
	public void generatedTest38() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource38);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator > is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource39 = "form Form1 {\"\" q0: string 1 > \"string\"}";

	@Test
	public void generatedTest39() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource39);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator > is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource40 = "form Form1 {\"\" q0: string true > true}";

	@Test
	public void generatedTest40() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource40);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource41 = "form Form1 {\"\" q0: string true > \"string\"}";

	@Test
	public void generatedTest41() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource41);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator > is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource42 = "form Form1 {\"\" q0: string \"string\" > \"string\"}";

	@Test
	public void generatedTest42() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource42);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource43 = "form Form1 {\"\" q0: string 1 >= 1}";

	@Test
	public void generatedTest43() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource43);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource44 = "form Form1 {\"\" q0: string 1 >= true}";

	@Test
	public void generatedTest44() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource44);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator >= is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource45 = "form Form1 {\"\" q0: string 1 >= \"string\"}";

	@Test
	public void generatedTest45() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource45);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator >= is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource46 = "form Form1 {\"\" q0: string true >= true}";

	@Test
	public void generatedTest46() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource46);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource47 = "form Form1 {\"\" q0: string true >= \"string\"}";

	@Test
	public void generatedTest47() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource47);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator >= is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource48 = "form Form1 {\"\" q0: string \"string\" >= \"string\"}";

	@Test
	public void generatedTest48() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource48);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource49 = "form Form1 {\"\" q0: string 1 < 1}";

	@Test
	public void generatedTest49() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource49);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource50 = "form Form1 {\"\" q0: string 1 < true}";

	@Test
	public void generatedTest50() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource50);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator < is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource51 = "form Form1 {\"\" q0: string 1 < \"string\"}";

	@Test
	public void generatedTest51() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource51);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator < is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource52 = "form Form1 {\"\" q0: string true < true}";

	@Test
	public void generatedTest52() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource52);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource53 = "form Form1 {\"\" q0: string true < \"string\"}";

	@Test
	public void generatedTest53() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource53);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator < is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource54 = "form Form1 {\"\" q0: string \"string\" < \"string\"}";

	@Test
	public void generatedTest54() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource54);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource55 = "form Form1 {\"\" q0: string 1 <= 1}";

	@Test
	public void generatedTest55() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource55);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource56 = "form Form1 {\"\" q0: string 1 <= true}";

	@Test
	public void generatedTest56() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource56);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator <= is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource57 = "form Form1 {\"\" q0: string 1 <= \"string\"}";

	@Test
	public void generatedTest57() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource57);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator <= is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource58 = "form Form1 {\"\" q0: string true <= true}";

	@Test
	public void generatedTest58() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource58);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource59 = "form Form1 {\"\" q0: string true <= \"string\"}";

	@Test
	public void generatedTest59() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource59);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator <= is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource60 = "form Form1 {\"\" q0: string \"string\" <= \"string\"}";

	@Test
	public void generatedTest60() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource60);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource61 = "form Form1 {\"\" q0: string 1 == 1}";

	@Test
	public void generatedTest61() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource61);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource62 = "form Form1 {\"\" q0: string 1 == true}";

	@Test
	public void generatedTest62() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource62);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator == is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource63 = "form Form1 {\"\" q0: string 1 == \"string\"}";

	@Test
	public void generatedTest63() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource63);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator == is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource64 = "form Form1 {\"\" q0: string true == true}";

	@Test
	public void generatedTest64() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource64);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource65 = "form Form1 {\"\" q0: string true == \"string\"}";

	@Test
	public void generatedTest65() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource65);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator == is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource66 = "form Form1 {\"\" q0: string \"string\" == \"string\"}";

	@Test
	public void generatedTest66() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource66);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource67 = "form Form1 {\"\" q0: string 1 != 1}";

	@Test
	public void generatedTest67() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource67);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource68 = "form Form1 {\"\" q0: string 1 != true}";

	@Test
	public void generatedTest68() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource68);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator != is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource69 = "form Form1 {\"\" q0: string 1 != \"string\"}";

	@Test
	public void generatedTest69() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource69);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 28: Operator != is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource70 = "form Form1 {\"\" q0: string true != true}";

	@Test
	public void generatedTest70() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource70);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource71 = "form Form1 {\"\" q0: string true != \"string\"}";

	@Test
	public void generatedTest71() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource71);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 31: Operator != is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource72 = "form Form1 {\"\" q0: string \"string\" != \"string\"}";

	@Test
	public void generatedTest72() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource72);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 26: Type mismatch, expected string, but got boolean"));
	}

	String generatedSource73 = "form Form1 {\"\" q0: integer 1 + 1}";

	@Test
	public void generatedTest73() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource73);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource74 = "form Form1 {\"\" q0: integer 1 + true}";

	@Test
	public void generatedTest74() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource74);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator + is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource75 = "form Form1 {\"\" q0: integer 1 + \"string\"}";

	@Test
	public void generatedTest75() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource75);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator + is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource76 = "form Form1 {\"\" q0: integer true + true}";

	@Test
	public void generatedTest76() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource76);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource77 = "form Form1 {\"\" q0: integer true + \"string\"}";

	@Test
	public void generatedTest77() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource77);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator + is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource78 = "form Form1 {\"\" q0: integer \"string\" + \"string\"}";

	@Test
	public void generatedTest78() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource78);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got string"));
	}

	String generatedSource79 = "form Form1 {\"\" q0: integer 1 - 1}";

	@Test
	public void generatedTest79() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource79);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource80 = "form Form1 {\"\" q0: integer 1 - true}";

	@Test
	public void generatedTest80() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource80);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator - is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource81 = "form Form1 {\"\" q0: integer 1 - \"string\"}";

	@Test
	public void generatedTest81() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource81);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator - is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource82 = "form Form1 {\"\" q0: integer true - true}";

	@Test
	public void generatedTest82() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource82);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource83 = "form Form1 {\"\" q0: integer true - \"string\"}";

	@Test
	public void generatedTest83() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource83);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator - is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource84 = "form Form1 {\"\" q0: integer \"string\" - \"string\"}";

	@Test
	public void generatedTest84() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource84);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource85 = "form Form1 {\"\" q0: integer 1 * 1}";

	@Test
	public void generatedTest85() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource85);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource86 = "form Form1 {\"\" q0: integer 1 * true}";

	@Test
	public void generatedTest86() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource86);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator * is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource87 = "form Form1 {\"\" q0: integer 1 * \"string\"}";

	@Test
	public void generatedTest87() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource87);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator * is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource88 = "form Form1 {\"\" q0: integer true * true}";

	@Test
	public void generatedTest88() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource88);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource89 = "form Form1 {\"\" q0: integer true * \"string\"}";

	@Test
	public void generatedTest89() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource89);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator * is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource90 = "form Form1 {\"\" q0: integer \"string\" * \"string\"}";

	@Test
	public void generatedTest90() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource90);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource91 = "form Form1 {\"\" q0: integer 1 / 1}";

	@Test
	public void generatedTest91() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource91);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource92 = "form Form1 {\"\" q0: integer 1 / true}";

	@Test
	public void generatedTest92() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource92);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator / is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource93 = "form Form1 {\"\" q0: integer 1 / \"string\"}";

	@Test
	public void generatedTest93() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource93);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator / is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource94 = "form Form1 {\"\" q0: integer true / true}";

	@Test
	public void generatedTest94() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource94);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource95 = "form Form1 {\"\" q0: integer true / \"string\"}";

	@Test
	public void generatedTest95() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource95);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator / is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource96 = "form Form1 {\"\" q0: integer \"string\" / \"string\"}";

	@Test
	public void generatedTest96() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource96);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got unknown type"));
	}

	String generatedSource97 = "form Form1 {\"\" q0: integer 1 && 1}";

	@Test
	public void generatedTest97() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource97);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: integer, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource98 = "form Form1 {\"\" q0: integer 1 && true}";

	@Test
	public void generatedTest98() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource98);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator && is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource99 = "form Form1 {\"\" q0: integer 1 && \"string\"}";

	@Test
	public void generatedTest99() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource99);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator && is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource100 = "form Form1 {\"\" q0: integer true && true}";

	@Test
	public void generatedTest100() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource100);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource101 = "form Form1 {\"\" q0: integer true && \"string\"}";

	@Test
	public void generatedTest101() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource101);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator && is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource102 = "form Form1 {\"\" q0: integer \"string\" && \"string\"}";

	@Test
	public void generatedTest102() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource102);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource103 = "form Form1 {\"\" q0: integer 1 || 1}";

	@Test
	public void generatedTest103() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource103);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: integer, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource104 = "form Form1 {\"\" q0: integer 1 || true}";

	@Test
	public void generatedTest104() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource104);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator || is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource105 = "form Form1 {\"\" q0: integer 1 || \"string\"}";

	@Test
	public void generatedTest105() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource105);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator || is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource106 = "form Form1 {\"\" q0: integer true || true}";

	@Test
	public void generatedTest106() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource106);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource107 = "form Form1 {\"\" q0: integer true || \"string\"}";

	@Test
	public void generatedTest107() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource107);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator || is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource108 = "form Form1 {\"\" q0: integer \"string\" || \"string\"}";

	@Test
	public void generatedTest108() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource108);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource109 = "form Form1 {\"\" q0: integer 1 > 1}";

	@Test
	public void generatedTest109() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource109);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource110 = "form Form1 {\"\" q0: integer 1 > true}";

	@Test
	public void generatedTest110() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource110);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator > is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource111 = "form Form1 {\"\" q0: integer 1 > \"string\"}";

	@Test
	public void generatedTest111() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource111);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator > is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource112 = "form Form1 {\"\" q0: integer true > true}";

	@Test
	public void generatedTest112() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource112);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource113 = "form Form1 {\"\" q0: integer true > \"string\"}";

	@Test
	public void generatedTest113() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource113);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator > is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource114 = "form Form1 {\"\" q0: integer \"string\" > \"string\"}";

	@Test
	public void generatedTest114() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource114);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource115 = "form Form1 {\"\" q0: integer 1 >= 1}";

	@Test
	public void generatedTest115() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource115);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource116 = "form Form1 {\"\" q0: integer 1 >= true}";

	@Test
	public void generatedTest116() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource116);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator >= is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource117 = "form Form1 {\"\" q0: integer 1 >= \"string\"}";

	@Test
	public void generatedTest117() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource117);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator >= is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource118 = "form Form1 {\"\" q0: integer true >= true}";

	@Test
	public void generatedTest118() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource118);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource119 = "form Form1 {\"\" q0: integer true >= \"string\"}";

	@Test
	public void generatedTest119() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource119);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator >= is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource120 = "form Form1 {\"\" q0: integer \"string\" >= \"string\"}";

	@Test
	public void generatedTest120() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource120);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource121 = "form Form1 {\"\" q0: integer 1 < 1}";

	@Test
	public void generatedTest121() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource121);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource122 = "form Form1 {\"\" q0: integer 1 < true}";

	@Test
	public void generatedTest122() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource122);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator < is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource123 = "form Form1 {\"\" q0: integer 1 < \"string\"}";

	@Test
	public void generatedTest123() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource123);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator < is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource124 = "form Form1 {\"\" q0: integer true < true}";

	@Test
	public void generatedTest124() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource124);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource125 = "form Form1 {\"\" q0: integer true < \"string\"}";

	@Test
	public void generatedTest125() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource125);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator < is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource126 = "form Form1 {\"\" q0: integer \"string\" < \"string\"}";

	@Test
	public void generatedTest126() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource126);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource127 = "form Form1 {\"\" q0: integer 1 <= 1}";

	@Test
	public void generatedTest127() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource127);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource128 = "form Form1 {\"\" q0: integer 1 <= true}";

	@Test
	public void generatedTest128() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource128);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator <= is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource129 = "form Form1 {\"\" q0: integer 1 <= \"string\"}";

	@Test
	public void generatedTest129() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource129);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator <= is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource130 = "form Form1 {\"\" q0: integer true <= true}";

	@Test
	public void generatedTest130() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource130);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource131 = "form Form1 {\"\" q0: integer true <= \"string\"}";

	@Test
	public void generatedTest131() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource131);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator <= is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource132 = "form Form1 {\"\" q0: integer \"string\" <= \"string\"}";

	@Test
	public void generatedTest132() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource132);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource133 = "form Form1 {\"\" q0: integer 1 == 1}";

	@Test
	public void generatedTest133() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource133);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource134 = "form Form1 {\"\" q0: integer 1 == true}";

	@Test
	public void generatedTest134() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource134);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator == is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource135 = "form Form1 {\"\" q0: integer 1 == \"string\"}";

	@Test
	public void generatedTest135() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource135);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator == is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource136 = "form Form1 {\"\" q0: integer true == true}";

	@Test
	public void generatedTest136() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource136);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource137 = "form Form1 {\"\" q0: integer true == \"string\"}";

	@Test
	public void generatedTest137() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource137);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator == is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource138 = "form Form1 {\"\" q0: integer \"string\" == \"string\"}";

	@Test
	public void generatedTest138() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource138);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource139 = "form Form1 {\"\" q0: integer 1 != 1}";

	@Test
	public void generatedTest139() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource139);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource140 = "form Form1 {\"\" q0: integer 1 != true}";

	@Test
	public void generatedTest140() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource140);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator != is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource141 = "form Form1 {\"\" q0: integer 1 != \"string\"}";

	@Test
	public void generatedTest141() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource141);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator != is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource142 = "form Form1 {\"\" q0: integer true != true}";

	@Test
	public void generatedTest142() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource142);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource143 = "form Form1 {\"\" q0: integer true != \"string\"}";

	@Test
	public void generatedTest143() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource143);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator != is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource144 = "form Form1 {\"\" q0: integer \"string\" != \"string\"}";

	@Test
	public void generatedTest144() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource144);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected integer, but got boolean"));
	}

	String generatedSource145 = "form Form1 {\"\" q0: boolean 1 + 1}";

	@Test
	public void generatedTest145() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource145);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got integer"));
	}

	String generatedSource146 = "form Form1 {\"\" q0: boolean 1 + true}";

	@Test
	public void generatedTest146() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource146);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator + is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource147 = "form Form1 {\"\" q0: boolean 1 + \"string\"}";

	@Test
	public void generatedTest147() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource147);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator + is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource148 = "form Form1 {\"\" q0: boolean true + true}";

	@Test
	public void generatedTest148() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource148);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource149 = "form Form1 {\"\" q0: boolean true + \"string\"}";

	@Test
	public void generatedTest149() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource149);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator + is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource150 = "form Form1 {\"\" q0: boolean \"string\" + \"string\"}";

	@Test
	public void generatedTest150() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource150);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got string"));
	}

	String generatedSource151 = "form Form1 {\"\" q0: boolean 1 - 1}";

	@Test
	public void generatedTest151() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource151);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got integer"));
	}

	String generatedSource152 = "form Form1 {\"\" q0: boolean 1 - true}";

	@Test
	public void generatedTest152() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource152);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator - is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource153 = "form Form1 {\"\" q0: boolean 1 - \"string\"}";

	@Test
	public void generatedTest153() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource153);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator - is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource154 = "form Form1 {\"\" q0: boolean true - true}";

	@Test
	public void generatedTest154() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource154);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource155 = "form Form1 {\"\" q0: boolean true - \"string\"}";

	@Test
	public void generatedTest155() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource155);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator - is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource156 = "form Form1 {\"\" q0: boolean \"string\" - \"string\"}";

	@Test
	public void generatedTest156() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource156);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource157 = "form Form1 {\"\" q0: boolean 1 * 1}";

	@Test
	public void generatedTest157() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource157);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got integer"));
	}

	String generatedSource158 = "form Form1 {\"\" q0: boolean 1 * true}";

	@Test
	public void generatedTest158() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource158);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator * is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource159 = "form Form1 {\"\" q0: boolean 1 * \"string\"}";

	@Test
	public void generatedTest159() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource159);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator * is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource160 = "form Form1 {\"\" q0: boolean true * true}";

	@Test
	public void generatedTest160() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource160);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource161 = "form Form1 {\"\" q0: boolean true * \"string\"}";

	@Test
	public void generatedTest161() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource161);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator * is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource162 = "form Form1 {\"\" q0: boolean \"string\" * \"string\"}";

	@Test
	public void generatedTest162() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource162);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource163 = "form Form1 {\"\" q0: boolean 1 / 1}";

	@Test
	public void generatedTest163() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource163);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got integer"));
	}

	String generatedSource164 = "form Form1 {\"\" q0: boolean 1 / true}";

	@Test
	public void generatedTest164() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource164);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator / is undefined for types: integer and boolean"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource165 = "form Form1 {\"\" q0: boolean 1 / \"string\"}";

	@Test
	public void generatedTest165() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource165);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator / is undefined for types: integer and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource166 = "form Form1 {\"\" q0: boolean true / true}";

	@Test
	public void generatedTest166() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource166);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource167 = "form Form1 {\"\" q0: boolean true / \"string\"}";

	@Test
	public void generatedTest167() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource167);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator / is undefined for types: boolean and string"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource168 = "form Form1 {\"\" q0: boolean \"string\" / \"string\"}";

	@Test
	public void generatedTest168() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource168);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 2);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: integer"));
		assertTrue(typeResolver.
			getErrorAtIndex(1).equals("[TypeResolver] line: 1, column: 27: Type mismatch, expected boolean, but got unknown type"));
	}

	String generatedSource169 = "form Form1 {\"\" q0: boolean 1 && 1}";

	@Test
	public void generatedTest169() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource169);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: integer, allowed types: boolean"));
	}

	String generatedSource170 = "form Form1 {\"\" q0: boolean 1 && true}";

	@Test
	public void generatedTest170() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource170);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator && is undefined for types: integer and boolean"));
	}

	String generatedSource171 = "form Form1 {\"\" q0: boolean 1 && \"string\"}";

	@Test
	public void generatedTest171() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource171);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator && is undefined for types: integer and string"));
	}

	String generatedSource172 = "form Form1 {\"\" q0: boolean true && true}";

	@Test
	public void generatedTest172() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource172);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource173 = "form Form1 {\"\" q0: boolean true && \"string\"}";

	@Test
	public void generatedTest173() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource173);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator && is undefined for types: boolean and string"));
	}

	String generatedSource174 = "form Form1 {\"\" q0: boolean \"string\" && \"string\"}";

	@Test
	public void generatedTest174() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource174);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: boolean"));
	}

	String generatedSource175 = "form Form1 {\"\" q0: boolean 1 || 1}";

	@Test
	public void generatedTest175() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource175);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: integer, allowed types: boolean"));
	}

	String generatedSource176 = "form Form1 {\"\" q0: boolean 1 || true}";

	@Test
	public void generatedTest176() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource176);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator || is undefined for types: integer and boolean"));
	}

	String generatedSource177 = "form Form1 {\"\" q0: boolean 1 || \"string\"}";

	@Test
	public void generatedTest177() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource177);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator || is undefined for types: integer and string"));
	}

	String generatedSource178 = "form Form1 {\"\" q0: boolean true || true}";

	@Test
	public void generatedTest178() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource178);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource179 = "form Form1 {\"\" q0: boolean true || \"string\"}";

	@Test
	public void generatedTest179() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource179);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator || is undefined for types: boolean and string"));
	}

	String generatedSource180 = "form Form1 {\"\" q0: boolean \"string\" || \"string\"}";

	@Test
	public void generatedTest180() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource180);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: string, allowed types: boolean"));
	}

	String generatedSource181 = "form Form1 {\"\" q0: boolean 1 > 1}";

	@Test
	public void generatedTest181() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource181);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource182 = "form Form1 {\"\" q0: boolean 1 > true}";

	@Test
	public void generatedTest182() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource182);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator > is undefined for types: integer and boolean"));
	}

	String generatedSource183 = "form Form1 {\"\" q0: boolean 1 > \"string\"}";

	@Test
	public void generatedTest183() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource183);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator > is undefined for types: integer and string"));
	}

	String generatedSource184 = "form Form1 {\"\" q0: boolean true > true}";

	@Test
	public void generatedTest184() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource184);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
	}

	String generatedSource185 = "form Form1 {\"\" q0: boolean true > \"string\"}";

	@Test
	public void generatedTest185() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource185);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator > is undefined for types: boolean and string"));
	}

	String generatedSource186 = "form Form1 {\"\" q0: boolean \"string\" > \"string\"}";

	@Test
	public void generatedTest186() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource186);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource187 = "form Form1 {\"\" q0: boolean 1 >= 1}";

	@Test
	public void generatedTest187() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource187);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource188 = "form Form1 {\"\" q0: boolean 1 >= true}";

	@Test
	public void generatedTest188() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource188);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator >= is undefined for types: integer and boolean"));
	}

	String generatedSource189 = "form Form1 {\"\" q0: boolean 1 >= \"string\"}";

	@Test
	public void generatedTest189() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource189);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator >= is undefined for types: integer and string"));
	}

	String generatedSource190 = "form Form1 {\"\" q0: boolean true >= true}";

	@Test
	public void generatedTest190() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource190);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
	}

	String generatedSource191 = "form Form1 {\"\" q0: boolean true >= \"string\"}";

	@Test
	public void generatedTest191() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource191);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator >= is undefined for types: boolean and string"));
	}

	String generatedSource192 = "form Form1 {\"\" q0: boolean \"string\" >= \"string\"}";

	@Test
	public void generatedTest192() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource192);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource193 = "form Form1 {\"\" q0: boolean 1 < 1}";

	@Test
	public void generatedTest193() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource193);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource194 = "form Form1 {\"\" q0: boolean 1 < true}";

	@Test
	public void generatedTest194() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource194);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator < is undefined for types: integer and boolean"));
	}

	String generatedSource195 = "form Form1 {\"\" q0: boolean 1 < \"string\"}";

	@Test
	public void generatedTest195() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource195);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator < is undefined for types: integer and string"));
	}

	String generatedSource196 = "form Form1 {\"\" q0: boolean true < true}";

	@Test
	public void generatedTest196() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource196);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
	}

	String generatedSource197 = "form Form1 {\"\" q0: boolean true < \"string\"}";

	@Test
	public void generatedTest197() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource197);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator < is undefined for types: boolean and string"));
	}

	String generatedSource198 = "form Form1 {\"\" q0: boolean \"string\" < \"string\"}";

	@Test
	public void generatedTest198() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource198);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource199 = "form Form1 {\"\" q0: boolean 1 <= 1}";

	@Test
	public void generatedTest199() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource199);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource200 = "form Form1 {\"\" q0: boolean 1 <= true}";

	@Test
	public void generatedTest200() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource200);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator <= is undefined for types: integer and boolean"));
	}

	String generatedSource201 = "form Form1 {\"\" q0: boolean 1 <= \"string\"}";

	@Test
	public void generatedTest201() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource201);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator <= is undefined for types: integer and string"));
	}

	String generatedSource202 = "form Form1 {\"\" q0: boolean true <= true}";

	@Test
	public void generatedTest202() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource202);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 27: Type mismatch, requested type: boolean, allowed types: integer or string"));
	}

	String generatedSource203 = "form Form1 {\"\" q0: boolean true <= \"string\"}";

	@Test
	public void generatedTest203() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource203);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator <= is undefined for types: boolean and string"));
	}

	String generatedSource204 = "form Form1 {\"\" q0: boolean \"string\" <= \"string\"}";

	@Test
	public void generatedTest204() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource204);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource205 = "form Form1 {\"\" q0: boolean 1 == 1}";

	@Test
	public void generatedTest205() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource205);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource206 = "form Form1 {\"\" q0: boolean 1 == true}";

	@Test
	public void generatedTest206() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource206);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator == is undefined for types: integer and boolean"));
	}

	String generatedSource207 = "form Form1 {\"\" q0: boolean 1 == \"string\"}";

	@Test
	public void generatedTest207() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource207);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator == is undefined for types: integer and string"));
	}

	String generatedSource208 = "form Form1 {\"\" q0: boolean true == true}";

	@Test
	public void generatedTest208() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource208);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource209 = "form Form1 {\"\" q0: boolean true == \"string\"}";

	@Test
	public void generatedTest209() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource209);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator == is undefined for types: boolean and string"));
	}

	String generatedSource210 = "form Form1 {\"\" q0: boolean \"string\" == \"string\"}";

	@Test
	public void generatedTest210() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource210);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource211 = "form Form1 {\"\" q0: boolean 1 != 1}";

	@Test
	public void generatedTest211() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource211);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource212 = "form Form1 {\"\" q0: boolean 1 != true}";

	@Test
	public void generatedTest212() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource212);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator != is undefined for types: integer and boolean"));
	}

	String generatedSource213 = "form Form1 {\"\" q0: boolean 1 != \"string\"}";

	@Test
	public void generatedTest213() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource213);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 29: Operator != is undefined for types: integer and string"));
	}

	String generatedSource214 = "form Form1 {\"\" q0: boolean true != true}";

	@Test
	public void generatedTest214() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource214);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}

	String generatedSource215 = "form Form1 {\"\" q0: boolean true != \"string\"}";

	@Test
	public void generatedTest215() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource215);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 1);
		assertTrue(typeResolver.
			getErrorAtIndex(0).equals("[TypeResolver] line: 1, column: 32: Operator != is undefined for types: boolean and string"));
	}

	String generatedSource216 = "form Form1 {\"\" q0: boolean \"string\" != \"string\"}";

	@Test
	public void generatedTest216() throws Exception {
		List<Stmt> ast = TestUtilities.buildAst(generatedSource216);
		typeResolver.resolve(ast);
		assertTrue(typeResolver.getNumberOfErrors() == 0);
	}
}