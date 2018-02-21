package org.uva.sc.cr.ql.interpreter

import java.util.Map
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.ExpressionAnd
import org.uva.sc.cr.ql.qL.ExpressionComparison
import org.uva.sc.cr.ql.qL.ExpressionEquality
import org.uva.sc.cr.ql.qL.ExpressionLiteralBoolean
import org.uva.sc.cr.ql.qL.ExpressionLiteralInteger
import org.uva.sc.cr.ql.qL.ExpressionLiteralString
import org.uva.sc.cr.ql.qL.ExpressionMulOrDiv
import org.uva.sc.cr.ql.qL.ExpressionNot
import org.uva.sc.cr.ql.qL.ExpressionOr
import org.uva.sc.cr.ql.qL.ExpressionPlusOrMinus
import org.uva.sc.cr.ql.qL.ExpressionQuestionRef
import org.uva.sc.cr.ql.util.MissingCaseException

class ExpressionEvaluator {

	val ScriptEngine engine;

	new() {
		val manager = new ScriptEngineManager()
		engine = manager.getEngineByName("js")
	}

	private def String buildExpression(Expression exp) {
		switch exp {
			ExpressionOr: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionAnd: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionEquality: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionComparison: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionPlusOrMinus: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionMulOrDiv: '''«buildExpression(exp.left)» «exp.op» «buildExpression(exp.right)»'''
			ExpressionNot:
				"!" + buildExpression(exp.expression)
			ExpressionLiteralString:
				" '" + exp.expression + "' "
			ExpressionLiteralInteger:
				" " + exp.expression + " "
			ExpressionLiteralBoolean:
				" " + exp.expression + " "
			ExpressionQuestionRef:
				exp.question.name
			default:
				throw new MissingCaseException
		}
	}

	def <T> evalExpression(Expression exp, Map<String, Object> arguments) {
		val stringExp = buildExpression(exp)

		arguments.forEach[variableName, valueValue |
			engine.put(variableName, valueValue)
		]
		println("evaluating " + stringExp + " with " + arguments)
		var result = engine.eval(stringExp)
		println("result " + result)
		return result as T
	}

}
