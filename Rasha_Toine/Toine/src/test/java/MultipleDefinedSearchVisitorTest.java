
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.visitors.MultipleDefinedSearchVisitor;
import utils.StaticUtils;

public class MultipleDefinedSearchVisitorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testExpectNoErrorThrown() throws Exception {

		MultipleDefinedSearchVisitor multipleDefinedSearchVisitor = new MultipleDefinedSearchVisitor();
		
		String testData = ""
				+ "form formId { "
				+ "	questionId: \"label\" boolean"
				+ "}";

		ExpressionLanguageParser expressionLanguageParser = StaticUtils.parseDataForTest(testData);

		ParseTree parseTree = expressionLanguageParser.form();

		// 
		// Expect the visit NOT to throw an exception
		//
		multipleDefinedSearchVisitor.visit(parseTree);

	}
	
	@Test
	public void testExpectErrorThrown() throws Exception {
		
		MultipleDefinedSearchVisitor multipleDefinedSearchVisitor = new MultipleDefinedSearchVisitor();

		String testData = ""
				+ "form formId { "
				+ "	questionId: \"label\" boolean"
				+ "	questionId: \"label\" boolean"
				+ "}";
		
		ExpressionLanguageParser expressionLanguageParser = StaticUtils.parseDataForTest(testData);

		ParseTree parseTree = expressionLanguageParser.form();

		//
		// Expect the coming visit to throw this exception
		//
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Multiple defined: questionId");

		multipleDefinedSearchVisitor.visit(parseTree);
		
	}

}