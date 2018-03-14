package node

import data.question.Question
import data.value.BooleanValue
import expression.Expression
import expression.visitor.evaluation.EvaluationVisitor

data class ExpressionNode(val expression: Expression) : Node() {

    private val visitor = EvaluationVisitor()

    override fun getEnabledQuestions(): ArrayList<Question> {

        val expressionResult = expression.accept(visitor) is BooleanValue

        if (expressionResult) {
            val allChildren = children.flatMap { child ->
                child.getEnabledQuestions()
            }

            return ArrayList(allChildren)
        }

        return ArrayList()
    }

    override fun validate(): Boolean {
        return children.all { child ->
            child.validate()
        }//TODO add expression validator
    }
}