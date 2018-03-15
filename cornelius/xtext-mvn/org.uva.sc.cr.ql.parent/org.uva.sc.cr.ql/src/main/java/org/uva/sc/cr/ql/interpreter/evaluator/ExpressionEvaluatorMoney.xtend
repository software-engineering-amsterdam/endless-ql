package org.uva.sc.cr.ql.interpreter.evaluator

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Map
import javax.inject.Singleton
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.uva.sc.cr.ql.qL.ExpressionMultiplicationOrDivision
import org.uva.sc.cr.ql.qL.ExpressionPlusOrMinus
import org.uva.sc.cr.ql.qL.ExpressionQuestionRef
import org.uva.sc.cr.ql.util.Operation

@Singleton
class ExpressionEvaluatorMoney {

	private static val CURRENCY_UNIT = CurrencyUnit.EUR
	private static val ROUNDING_MODE = RoundingMode.DOWN

	dispatch def Money evaluateExpression(ExpressionPlusOrMinus expression, Map<String, Object> arguments) {
		var leftMoney = evaluateExpression(expression.left, arguments)
		var rightMoney = evaluateExpression(expression.right, arguments)
		if (expression.op == Operation.PLUS.literal) {
			leftMoney.plus(rightMoney)
		} else {
			leftMoney.minus(rightMoney)
		}
	}

	dispatch def Money evaluateExpression(ExpressionMultiplicationOrDivision expression, Map<String, Object> arguments) {
		var leftMoney = evaluateExpression(expression.left, arguments)
		var rightMoney = evaluateExpression(expression.right, arguments)
		if (expression.op == Operation.MULTIPLICATION.literal)
			leftMoney.multipliedBy(rightMoney.amount, ROUNDING_MODE)
		else
			leftMoney.dividedBy(rightMoney.amount, ROUNDING_MODE)
	}

	dispatch def Money evaluateExpression(ExpressionQuestionRef expression, Map<String, Object> arguments) {
		val value = arguments.get(expression.question.name)
		var BigDecimal bigDecimalValue;
		try {
			bigDecimalValue = new BigDecimal(value.toString)
		} catch (NumberFormatException e) {
			bigDecimalValue = new BigDecimal(0)
		}
		return Money.of(CURRENCY_UNIT, bigDecimalValue, ROUNDING_MODE)
	}

}
