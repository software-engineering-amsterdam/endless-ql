package org.uva.sc.pc.ql.interpreter

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.ExpressionAnd
import org.uva.sc.pc.ql.qLang.ExpressionComparison
import org.uva.sc.pc.ql.qLang.ExpressionEquality
import org.uva.sc.pc.ql.qLang.ExpressionLiteralBoolean
import org.uva.sc.pc.ql.qLang.ExpressionLiteralInteger
import org.uva.sc.pc.ql.qLang.ExpressionLiteralString
import org.uva.sc.pc.ql.qLang.ExpressionMulOrDiv
import org.uva.sc.pc.ql.qLang.ExpressionNot
import org.uva.sc.pc.ql.qLang.ExpressionOr
import org.uva.sc.pc.ql.qLang.ExpressionPlusOrMinus
import org.uva.sc.pc.ql.qLang.ExpressionQuestionRef
import org.uva.sc.pc.ql.qLang.util.MissingCaseException

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

	def <T> evalExpression(Expression exp) {
		val stringExp = buildExpression(exp)

		exp.eAllContents.filter[it instanceof ExpressionQuestionRef].forEach [
			var name = (it as ExpressionQuestionRef).question.name
			var widget = JavaFxMain.CONTROLS.get(name)
			switch widget {
				CheckBox: engine.put(name, widget.selected)
				TextField: engine.put(name, widget.text)
			}
		]
		var result = engine.eval(stringExp)
		return result as T
	}

}
