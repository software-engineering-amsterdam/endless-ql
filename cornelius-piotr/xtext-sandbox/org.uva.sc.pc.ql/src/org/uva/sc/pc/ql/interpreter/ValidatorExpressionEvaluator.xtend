package org.uva.sc.pc.ql.interpreter

import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.ExpressionQuestionRef
import org.uva.sc.pc.ql.qLang.util.TypeUtil
import java.util.Random
import org.uva.sc.pc.ql.qLang.util.MissingCaseException

//TODO is unused - check if we actually need it
class ValidatorExpressionEvaluator extends ExpressionEvaluator {

	def evalExpression(Expression exp) {
		val stringExp = buildExpression(exp)

		exp.eAllContents.filter[it instanceof ExpressionQuestionRef].forEach [
			val question = (it as ExpressionQuestionRef).question
			val name = question.name
			val type = TypeUtil.getTypeForQuestionType(question.type)
			switch type {
				case TypeUtil.TYPE_BOOLEAN: engine.put(name, Random.newInstance.nextBoolean)
				case TypeUtil.TYPE_INTEGER: engine.put(name, Random.newInstance.nextInt)
				case TypeUtil.TYPE_MONEY: engine.put(name, Random.newInstance.nextDouble)
				default: throw new MissingCaseException
			}
		]
		val result = engine.eval(stringExp)
		return result
	}

}
