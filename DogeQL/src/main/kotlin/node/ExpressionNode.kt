package node

import data.question.Question
import data.value.BaseSymbolValue
import expression.Expression
import expression.visitor.evaluation.ReferenceProvider

data class ExpressionNode(val expression: Expression) : Node(), ReferenceProvider {

    override fun findReference(name: String): BaseSymbolValue {
        return findValueForReference(name) ?: throw NoSuchElementException()
    }

    override fun getEnabledQuestions(): ArrayList<Question> {

//        val visitor = EvaluationVisitor()
//
//        val expressionResult = expression.accept(visitor) as BooleanValue
//
//        if (expressionResult.value) {
//            val allChildren = children.flatMap { child ->
//                child.getEnabledQuestions()
//            }
//
//            return ArrayList(allChildren)
//        }

        return ArrayList()
    }

    override fun validate(): Boolean {
        return children.all { child ->
            child.validate()
        }//TODO add expression validator
    }
}