package node

import data.question.Question

abstract class Node {
    internal val children = ArrayList<Node>()

    fun addChild(child: Node) {
        children.add(child)
    }

    fun getChildren(): ArrayList<Node> {
        return children
    }

    fun hasChildren(): Boolean {
        return children.isEmpty()
    }

    open fun getAllChildren(): ArrayList<Question> {
        val allChildren = children.flatMap {
            getAllChildren()
        }

        return ArrayList(allChildren)
    }

    abstract fun validate(): Boolean
}