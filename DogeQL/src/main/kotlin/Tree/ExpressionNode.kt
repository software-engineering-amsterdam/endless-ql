package Tree

data class ExpressionNode(val identifier: String, val leftNode: ExpressionNode? = null, val rightNode: ExpressionNode? = null) {

    fun print() {
        print("(")
        leftNode?.print()
        print(identifier)
        rightNode?.print()
        print(")")
    }
}