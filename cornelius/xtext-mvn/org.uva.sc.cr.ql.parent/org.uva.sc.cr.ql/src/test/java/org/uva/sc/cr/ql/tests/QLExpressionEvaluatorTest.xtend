package org.uva.sc.cr.ql.tests

import com.google.inject.Inject
import java.util.HashMap
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluator
import org.uva.sc.cr.ql.qL.Form

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLExpressionEvaluatorTest {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Inject
	ExpressionEvaluator expressionEvaluator

	@Test
	def void testOrEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a house?" q2: boolean
				if(q1 || q2){
					"Do you have a dog?" q1_1: boolean = (q1 && q2)
					"Do you have a cat?" q1_2: boolean = (q1 || q2)
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.blocks.head.expression

		val arguments = new HashMap<String, Object>
		var Boolean expressionResult = null

		arguments.put("q1", false)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertTrue(expressionResult)

		arguments.put("q1", false)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertTrue(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertTrue(expressionResult)

	}

	@Test
	def void testAndEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a house?" q2: boolean
				if(q1 && q2){
					"Do you have a dog?" q1_1: boolean = (q1 && q2)
					"Do you have a cat?" q1_2: boolean = (q1 || q2)
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.blocks.head.expression

		val arguments = new HashMap<String, Object>
		var Boolean expressionResult = null

		arguments.put("q1", false)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", false)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Boolean)
		Assert.assertTrue(expressionResult)

	}

	@Test
	def void testComplexIntegerEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"q?" q1: integer
				"q?" q2: integer
				"q?" q3: integer = (q1 + 5 + q2 * 5 - 10)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.questions.get(2).expression

		val arguments = new HashMap<String, Object>
		var Double expressionResult = null

		arguments.put("q1", 3)
		arguments.put("q2", 6)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Double)
		Assert.assertEquals(28, expressionResult.intValue)

	}

	@Test
	def void testComplexDoubleEvaluation() {
		val result = parseHelper.parse('''
			form TestForm{
				"q?" q1: integer
				"q?" q2: integer
				"q?" q3: integer = (q1 + 5 + q2 * 2 - 10 / 3)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
		validationTestHelper.assertNoErrors(result)

		val expression = result.body.questions.get(2).expression

		val arguments = new HashMap<String, Object>
		var Double expressionResult = null

		arguments.put("q1", 3.5)
		arguments.put("q2", 9.8)
		expressionResult = expressionEvaluator.evaluateExpression(expression, arguments, Double)
		Assert.assertEquals(24.7666, expressionResult.doubleValue, 0.0001)
		println(expressionResult)
	}

}
