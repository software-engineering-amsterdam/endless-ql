
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.visitors.TypeCheckingVisitor;
import utils.AbstractParserFactory;

public class Test_TypeChecker {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test_ExpectNoErrorThrown() throws Exception {

		TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();
		
		String testData =
				"form Box1HouseOwning {																			"+
				"   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"+
				"   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"+
				"   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"+
				"																								"+
				"	if (hasSoldHouse) {																			"+
				"		sellingPrice: \"Price the house was sold for:\" money										"+
				"		privateDebt: \"Private debts for the sold house:\" money									"+
				"  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt)						"+
				"  	}																							"+
				"}																								";
		
		ExpressionLanguageParser expressionLanguageParser = AbstractParserFactory.parseDataForTest(testData);

		ParseTree parseTree = expressionLanguageParser.form();

		// 
		// Expect the visit NOT to throw an exception
		//
		typeCheckingVisitor.visit(parseTree).intValue();

	}
	
	@Test
	public void test_ExpectErrorThrown() throws Exception {
		
		TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();

		String testData = ""
				+ "form formId { "
				+ "	questionId: \"label\" boolean"
				+ "	questionId: \"label\" boolean"
				+ "}";
		
		ExpressionLanguageParser expressionLanguageParser = AbstractParserFactory.parseDataForTest(testData);

		ParseTree parseTree = expressionLanguageParser.form();

		//
		// Expect the coming visit to throw this exception
		//
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(TypeCheckingVisitor.ERROR_DuplicateQuestionDeclaration + "questionId");

		typeCheckingVisitor.visit(parseTree);
		
	}
	@Test
	public void testExpectUndefinedErrorThrown() throws Exception {
		
		TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();
		
		String testData = ""+
				"form formId {																					"+
				"  	forward: \"Some label:\" money (undefinedQuestionId)											"+
				"   questionId: \"label\" boolean																"+
				"}																								";
		
		ExpressionLanguageParser expressionLanguageParser = AbstractParserFactory.parseDataForTest(testData);
		
		ParseTree parseTree = expressionLanguageParser.form();
		
		//
		// Expect the coming visit to throw this exception
		//
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage( TypeCheckingVisitor.ERROR_ReferenceToUndefinedQuestion + "undefinedQuestionId");

		typeCheckingVisitor.visit(parseTree);
		
	}

}