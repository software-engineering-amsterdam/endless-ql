package org.uva.jomi.ql.tests.utilities;

import java.util.HashMap;

public class InterpreterTestGenerator {
	
	private final String[] testSources;
	private int testNumber = 1;
	
	HashMap<String, String> map = new HashMap<String, String>() {{
		put("1", "Integer");
		put("true", "Boolean");
		put("\"string\"", "String");
		put("+", "add");
		put("-", "subtract");
		put("*", "multiply");
		put("/", "divide");
		put("||", "perform an Or operation using");
		put("&&", "peform an And operation using");
		put("==", "compare");
		put("!=", "compare");
		put(">", "compare");
		put(">=", "compare");
		put("<", "compare");
		put("<=", "compare");
	}};
	
	public InterpreterTestGenerator(String[] testStrings) {
		this.testSources = testStrings;
	}
	
	public void generateTypeResolverTests() {
		for (String testSource : testSources) {
			
			String firstType = testSource.split(" ")[5];
			String operator = testSource.split(" ")[6];
			String secondType = testSource.split(" ")[7];
			
			System.out.format("	String generatedSource%d = \"%s\";\n\n", testNumber, testSource.replaceAll("\"", "\\\\\""));
			
			
			System.out.format("	@Test\n	public void generatedTest%d() throws Exception {\n", testNumber);
			System.out.format("		List<Stmt> ast = TestUtilities.buildAst(generatedSource%d);\n", testNumber);
			System.out.format("		try {\n");
			System.out.format("			interpreter.interpret(ast);\n");
			System.out.format("			fail(\"Test Failed\");\n");
			System.out.println("		}\n");
			System.out.format("		catch (Exception e) {\n");
			System.out.format("			assertTrue(e.getMessage().equals(\"RuntimeError: Cannot %s a %sValue and a %sValue\"));\n",
					map.get(operator), map.get(firstType), map.get(secondType));
			System.out.println("		}");
			System.out.println("	}\n");
			testNumber++;
		}
	}
	
	
	public static void main(String[] args) {
		String[] testSources = { "form Form1 {\"\" q0: integer 1 + true }",
				"form Form1 {\"\" q0: integer true + true }",
				"form Form1 {\"\" q0: integer true + \"string\" }",
				"form Form1 {\"\" q0: integer 1 - true }",
				"form Form1 {\"\" q0: integer 1 - \"string\" }",
				"form Form1 {\"\" q0: integer true - true }",
				"form Form1 {\"\" q0: integer true - \"string\" }",
				"form Form1 {\"\" q0: integer \"string\" - \"string\" }",
				"form Form1 {\"\" q0: integer 1 * true }",
				"form Form1 {\"\" q0: integer 1 * \"string\" }",
				"form Form1 {\"\" q0: integer true * true }",
				"form Form1 {\"\" q0: integer true * \"string\" }",
				"form Form1 {\"\" q0: integer \"string\" * \"string\" }",
				"form Form1 {\"\" q0: integer 1 / true }",
				"form Form1 {\"\" q0: integer 1 / \"string\" }",
				"form Form1 {\"\" q0: integer true / true }",
				"form Form1 {\"\" q0: integer true / \"string\" }",
				"form Form1 {\"\" q0: integer \"string\" / \"string\" }",
				"form Form1 {\"\" q0: integer 1 && 1 }",
				"form Form1 {\"\" q0: integer 1 && true }",
				"form Form1 {\"\" q0: integer 1 && \"string\" }",
				"form Form1 {\"\" q0: integer true && \"string\" }",
				"form Form1 {\"\" q0: integer \"string\" && \"string\" }",
				"form Form1 {\"\" q0: integer 1 || 1 }",
				"form Form1 {\"\" q0: integer 1 || true }",
				"form Form1 {\"\" q0: integer 1 || \"string\" }",
				"form Form1 {\"\" q0: integer true || \"string\" }",
				"form Form1 {\"\" q0: integer \"string\" || \"string\" }",
				"form Form1 {\"\" q0: integer 1 > true }",
				"form Form1 {\"\" q0: integer true > true }",
				"form Form1 {\"\" q0: integer true > \"string\" }",
				"form Form1 {\"\" q0: integer 1 >= true }",
				"form Form1 {\"\" q0: integer true >= true }",
				"form Form1 {\"\" q0: integer true >= \"string\" }",
				"form Form1 {\"\" q0: integer 1 < true }",
				"form Form1 {\"\" q0: integer true < true }",
				"form Form1 {\"\" q0: integer true < \"string\" }",
				"form Form1 {\"\" q0: integer 1 <= true }",
				"form Form1 {\"\" q0: integer true <= true }",
				"form Form1 {\"\" q0: integer true <= \"string\" }",
				"form Form1 {\"\" q0: integer 1 == true }",
				"form Form1 {\"\" q0: integer 1 == \"string\" }",
				"form Form1 {\"\" q0: integer true == \"string\" }",
				"form Form1 {\"\" q0: integer 1 != true }",
				"form Form1 {\"\" q0: integer 1 != \"string\" }",
				"form Form1 {\"\" q0: integer true != \"string\" }", 
				};
		
		InterpreterTestGenerator testGenerator = new InterpreterTestGenerator(testSources);
		testGenerator.generateTypeResolverTests();
		
	}

}
