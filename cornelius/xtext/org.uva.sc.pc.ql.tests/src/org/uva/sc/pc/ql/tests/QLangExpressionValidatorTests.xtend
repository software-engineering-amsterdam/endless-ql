package org.uva.sc.pc.ql.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.pc.ql.qLang.Form
import org.uva.sc.pc.ql.qLang.QLangPackage
import org.uva.sc.pc.ql.validation.QLangExpressionValidator
import org.uva.sc.pc.ql.util.QLangUtil

@RunWith(XtextRunner)
@InjectWith(QLangInjectorProvider)
class QLangExpressionValidatorTests {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testBooleanVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_EQUALS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT_EQUALS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN_EQUALS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN_EUQALS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_PLUS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MINUS, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MUL, QLangUtil.TYPE_BOOLEAN)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_DIV, QLangUtil.TYPE_BOOLEAN)
	}

	@Test
	def void testStringVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_OR, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_AND, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN_EQUALS, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN_EUQALS, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MINUS, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MUL, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_DIV, QLangUtil.TYPE_STRING)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT, QLangUtil.TYPE_STRING)
	}
	
	@Test
	def void testIntegerVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_OR, QLangUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_AND, QLangUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_EQUALS, QLangUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT_EQUALS, QLangUtil.TYPE_INTEGER)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT, QLangUtil.TYPE_INTEGER)
	}
	
	@Test
	def void testDecimalVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_OR, QLangUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_AND, QLangUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_EQUALS, QLangUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT_EQUALS, QLangUtil.TYPE_DECIMAL)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT, QLangUtil.TYPE_DECIMAL)
	}
	
	@Test
	def void testMoneyVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_OR, QLangUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_AND, QLangUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_EQUALS, QLangUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT_EQUALS, QLangUtil.TYPE_MONEY)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT, QLangUtil.TYPE_MONEY)
	}
	
	@Test
	def void testDateVariableValidityInExpression() {
		assertVariableInExpressionHelper(QLangUtil.OPERATION_OR, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_AND, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_EQUALS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT_EQUALS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_SMALLER_THAN_EQUALS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_GREATER_THAN_EUQALS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_PLUS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MINUS, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_MUL, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_DIV, QLangUtil.TYPE_DATE)
		assertVariableInExpressionHelper(QLangUtil.OPERATION_NOT, QLangUtil.TYPE_DATE)
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
			case QLangUtil.OPERATION_OR:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionOr,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_AND:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionAnd,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_EQUALS,
			case QLangUtil.OPERATION_NOT_EQUALS:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionEquality,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_SMALLER_THAN,
			case QLangUtil.OPERATION_SMALLER_THAN_EQUALS,
			case QLangUtil.OPERATION_GREATER_THAN,
			case QLangUtil.OPERATION_GREATER_THAN_EUQALS:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionComparison,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_PLUS,
			case QLangUtil.OPERATION_MINUS:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionPlusOrMinus,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_MUL,
			case QLangUtil.OPERATION_DIV:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionMulOrDiv,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
			case QLangUtil.OPERATION_NOT:
				validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionNot,
					QLangExpressionValidator.TYPE_NOT_ALLOWED)
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
		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.block,
					QLangExpressionValidator.BLOCK_INVALID_EXPRESSION)
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
		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.question,
					QLangExpressionValidator.TYPE_NOT_EXPECTED)
	}

}
