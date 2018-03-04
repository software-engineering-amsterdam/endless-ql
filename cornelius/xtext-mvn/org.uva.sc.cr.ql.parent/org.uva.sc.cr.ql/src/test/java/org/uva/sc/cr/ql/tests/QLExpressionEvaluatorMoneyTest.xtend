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
import java.math.BigDecimal

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLExpressionEvaluatorMoneyTest {

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
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, CurrencyUnit.EUR)
		Assert.assertTrue(expressionResult.equals(Money.of(CurrencyUnit.EUR, 13.30)))
		
	}

}
