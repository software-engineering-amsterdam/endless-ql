package org.uva.sc.cr.ql.tests

import java.util.HashMap
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluatorMoney
import org.uva.sc.cr.ql.qL.Form

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLExpressionEvaluatorMoneyTest {
	
	private static val CURRENCY_UNIT = CurrencyUnit.EUR

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Inject
	ExpressionEvaluatorMoney expressionEvaluator

	@Test
	def void testMoneyEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"q?" q1: money
				"q?" q2: money
				"q?" q3: money = (q1 + q2)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.questions.get(2).expression

		val arguments = new HashMap<String, Object>
		var Money expressionResult = null

		arguments.put("q1", 3.5)
		arguments.put("q2", 9.8)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult.equals(Money.of(CURRENCY_UNIT, 13.30)))
		
	}
	
	@Test
	def void testComplexMoneyEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"q?" q1: money
				"q?" q2: money
				"q?" q3: money
				"q?" q4: money
				"q?" q5: money
				"q?" q6: money = (q1 + q2 * q3 - q4 / q5)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.questions.get(5).expression

		val arguments = new HashMap<String, Object>
		var Money expressionResult = null

		arguments.put("q1", 3.5)
		arguments.put("q2", 9.8)
		arguments.put("q3", 3.2)
		arguments.put("q4", 88)
		arguments.put("q5", 7)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult.equals(Money.of(CURRENCY_UNIT, 22.29)))
	}
	
	@Test
	def void testMoneyEvaluationWithString() {
		val result = parseHelper.parse('''
			form TestForm{
				"q?" q1: money
				"q?" q2: money
				"q?" q3: money = (q1 + q2)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.questions.get(2).expression

		val arguments = new HashMap<String, Object>
		var Money expressionResult = null

		arguments.put("q1", "1qe")
		arguments.put("q2", 9.8)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult.equals(Money.of(CURRENCY_UNIT, 9.8)))
		
	}

}
