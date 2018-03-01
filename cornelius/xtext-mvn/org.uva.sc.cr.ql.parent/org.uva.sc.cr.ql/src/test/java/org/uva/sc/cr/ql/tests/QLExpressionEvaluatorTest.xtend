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
import org.uva.sc.cr.ql.interpreter.service.ExpressionEvaluator
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
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult)

		arguments.put("q1", false)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
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
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", false)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", false)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertFalse(expressionResult)

		arguments.put("q1", true)
		arguments.put("q2", true)
		expressionResult = expressionEvaluator.<Boolean>evaluateExpression(expression, arguments)
		Assert.assertTrue(expressionResult)

	}

}
