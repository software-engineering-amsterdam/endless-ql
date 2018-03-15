package org.uva.sc.cr.ql.util

import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.impl.ExpressionNotImpl
import org.uva.sc.cr.ql.qL.impl.ExpressionParanthesisImpl

class ExpressionUtil {
	def static Expression buildElseBlockExpression(Expression ifBlockExpression) {
		val parenthesisExpression = new ExpressionParanthesisImpl() {
		}
		parenthesisExpression.expression = ifBlockExpression
		val negatedExpression = new ExpressionNotImpl() {
		}
		negatedExpression.expression = parenthesisExpression
		return negatedExpression
	}
}
