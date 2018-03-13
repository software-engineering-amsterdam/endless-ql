package node

import expression.Expression

data class ExpressionNode(val expression: Expression) : Node() {

    override fun validate(): Boolean {
        return children.all {
            validate()
        }//TODO add expression validator
    }
}