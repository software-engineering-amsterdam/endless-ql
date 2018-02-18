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
import org.uva.sc.cr.ql.util.QLUtil
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
		assertVariableInExpressionHelper(QLUtil.OPERATION_EQUALS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT_EQUALS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN_EQUALS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN_EUQALS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_PLUS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MINUS, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MUL, QLUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLUtil.OPERATION_DIV, QLUtil.TYPE_BOOLEAN)
	}

	@Test
	def void testStringVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLUtil.OPERATION_OR, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_AND, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN_EQUALS, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN_EUQALS, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MINUS, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MUL, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_DIV, QLUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT, QLUtil.TYPE_STRING)
	}
	
	@Test
	def void testIntegerVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLUtil.OPERATION_OR, QLUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLUtil.OPERATION_AND, QLUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLUtil.OPERATION_EQUALS, QLUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT_EQUALS, QLUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT, QLUtil.TYPE_INTEGER)
	}
	
	@Test
	def void testDecimalVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLUtil.OPERATION_OR, QLUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLUtil.OPERATION_AND, QLUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLUtil.OPERATION_EQUALS, QLUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT_EQUALS, QLUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT, QLUtil.TYPE_DECIMAL)
	}
	
	@Test
	def void testMoneyVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLUtil.OPERATION_OR, QLUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLUtil.OPERATION_AND, QLUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLUtil.OPERATION_EQUALS, QLUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT_EQUALS, QLUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT, QLUtil.TYPE_MONEY)
	}
	
	@Test
	def void testDateVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLUtil.OPERATION_OR, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_AND, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_EQUALS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT_EQUALS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_SMALLER_THAN_EQUALS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_GREATER_THAN_EUQALS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_PLUS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MINUS, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_MUL, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_DIV, QLUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLUtil.OPERATION_NOT, QLUtil.TYPE_DATE)
	}

	def assertVariableInExpressionHelper(String op, String type) {
		val result = parseHelper.parse('''
			form TestForm{
							"Do you have a pet?" q1: «type»
							"Do you have a house?" q2: «type»
							"Computed" q3: «type» = («IF op != "!"»q1«ENDIF» «op» q2)
						}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		switch op {
			case QLUtil.OPERATION_OR:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionOr,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_AND:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionAnd,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_EQUALS,
			case QLUtil.OPERATION_NOT_EQUALS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionEquality,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_SMALLER_THAN,
			case QLUtil.OPERATION_SMALLER_THAN_EQUALS,
			case QLUtil.OPERATION_GREATER_THAN,
			case QLUtil.OPERATION_GREATER_THAN_EUQALS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionComparison,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_PLUS,
			case QLUtil.OPERATION_MINUS:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionPlusOrMinus,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_MUL,
			case QLUtil.OPERATION_DIV:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionMulOrDiv,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
			case QLUtil.OPERATION_NOT:
				validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionNot,
					QLExpressionValidator.TYPE_NOT_ALLOWED)
		}
	}
	
	@Test 
	def testErrorOnInvalidIfBlockExpression(){
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
	def testErrorOnInvalidComputedQuestionReturnType(){
		val result = parseHelper.parse('''
			form TestForm{
							"Do you have a pet?" q1: string
							"Do you have a house?" q2: string
							"Computed" q3: string = (q1 == q2)
						}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertError(result, QLPackage.eINSTANCE.question,
					QLExpressionValidator.TYPE_NOT_EXPECTED)
	}

}
