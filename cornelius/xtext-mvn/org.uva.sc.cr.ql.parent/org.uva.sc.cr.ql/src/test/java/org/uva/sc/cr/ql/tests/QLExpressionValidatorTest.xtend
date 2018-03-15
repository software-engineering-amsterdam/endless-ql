package org.uva.sc.cr.ql.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.ql.qL.QuestionType
import org.uva.sc.cr.ql.util.Operation
import org.uva.sc.cr.ql.validation.QLExpressionValidator

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLExpressionValidatorTest {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testBooleanVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.EQUALS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.NOT_EQUALS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN_EQUALS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.GREATER_THAN, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.GREATER_THAN_EQUALS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.PLUS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.MINUS, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.MULTIPLICATION, QuestionType.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(Operation.DIVISION, QuestionType.TYPE_BOOLEAN)
	}

	@Test
	def void testStringVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.OR, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.AND, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN_EQUALS, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.GREATER_THAN, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.GREATER_THAN_EQUALS, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.MINUS, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.MULTIPLICATION, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.DIVISION, QuestionType.TYPE_STRING)
		assertVariableInExpressionHelper(Operation.NOT, QuestionType.TYPE_STRING)
	}

	@Test
	def void testIntegerVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.OR, QuestionType.TYPE_INTEGER)
		assertVariableInExpressionHelper(Operation.AND, QuestionType.TYPE_INTEGER)
		assertVariableInExpressionHelper(Operation.EQUALS, QuestionType.TYPE_INTEGER)
		assertVariableInExpressionHelper(Operation.NOT_EQUALS, QuestionType.TYPE_INTEGER)
		assertVariableInExpressionHelper(Operation.NOT, QuestionType.TYPE_INTEGER)
	}

	@Test
	def void testDecimalVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.OR, QuestionType.TYPE_DECIMAL)
		assertVariableInExpressionHelper(Operation.AND, QuestionType.TYPE_DECIMAL)
		assertVariableInExpressionHelper(Operation.EQUALS, QuestionType.TYPE_DECIMAL)
		assertVariableInExpressionHelper(Operation.NOT_EQUALS, QuestionType.TYPE_DECIMAL)
		assertVariableInExpressionHelper(Operation.NOT, QuestionType.TYPE_DECIMAL)
	}

	@Test
	def void testMoneyVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.OR, QuestionType.TYPE_MONEY)
		assertVariableInExpressionHelper(Operation.AND, QuestionType.TYPE_MONEY)
		assertVariableInExpressionHelper(Operation.EQUALS, QuestionType.TYPE_MONEY)
		assertVariableInExpressionHelper(Operation.NOT_EQUALS, QuestionType.TYPE_MONEY)
		assertVariableInExpressionHelper(Operation.NOT, QuestionType.TYPE_MONEY)
	}

	@Test
	def void testDateVariableValidityInExpression() {
		assertVariableInExpressionHelper(Operation.OR, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.AND, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.EQUALS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.NOT_EQUALS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.SMALLER_THAN_EQUALS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.GREATER_THAN, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.GREATER_THAN_EQUALS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.PLUS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.MINUS, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.MULTIPLICATION, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.DIVISION, QuestionType.TYPE_DATE)
		assertVariableInExpressionHelper(Operation.NOT, QuestionType.TYPE_DATE)
	}

	def assertVariableInExpressionHelper(Operation operation, QuestionType type) {
		val result = parseHelper.parse('''
			form TestForm{
							"Do you have a pet?" q1: «type.literal»
							"Do you have a house?" q2: «type.literal»
							"Computed" q3: «type.literal» = («IF operation != Operation.NOT»q1«ENDIF» «operation.literal» q2)
						}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		switch operation {
			case OR:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionOr,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case AND:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionAnd,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case EQUALS,
			case NOT_EQUALS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionEquality,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case SMALLER_THAN,
			case SMALLER_THAN_EQUALS,
			case GREATER_THAN,
			case GREATER_THAN_EQUALS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionComparison,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case PLUS,
			case MINUS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionPlusOrMinus,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case MULTIPLICATION,
			case DIVISION:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionMultiplicationOrDivision,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case NOT:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionNot,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
		}
	}

	@Test
	def testErrorOnInvalidIfBlockExpression() {
		val result = parseHelper.parse('''
			form TestForm{
							"Do you have a pet?" q1: string
							"Do you have a house?" q2: string
							if(q1 + q2){
								"Computed" q3: string = (q1 + q2)
							}
						}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertError(result, QLPackage.eINSTANCE.block,
			QLExpressionValidator.BLOCK_INVALID_EXPRESSION)
	}

	@Test
	def testErrorOnInvalidComputedQuestionReturnType() {
		val result = parseHelper.parse('''
			form TestForm{
							"Do you have a pet?" q1: string
							"Do you have a house?" q2: string
							"Computed" q3: string = (q1 == q2)
						}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertError(result, QLPackage.eINSTANCE.question, QLExpressionValidator.TYPE_NOT_EXPECTED)
	}

}
