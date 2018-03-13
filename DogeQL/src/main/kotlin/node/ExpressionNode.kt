package node

import expression.Expression

data class ExpressionNode(val expression : Expression) : Node() {

    override fun validate(): Boolean {
        return children.all {
            x -> x.validate()
        }//TODO add expression validator
    }
}