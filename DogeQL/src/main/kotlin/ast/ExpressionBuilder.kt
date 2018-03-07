package ast

import expression.Expression
import java.util.*

class ExpressionBuilder {

    private val stack = ArrayDeque<Expression>()

    fun push(expression: Expression) {
        stack.push(expression)
    }

    fun pop(): Expression {
        return stack.pop()
    }

    fun build(): Expression {
        return stack.pop()
    }

}