package node

abstract class Node {
    internal val children = ArrayList<Node>()

    fun addChild(child : Node){
        children.add(child)
    }

    fun getChildren(): ArrayList<Node> {
        return children
    }

    fun hasChildren() : Boolean {
        return children.isEmpty()
    }

    abstract fun validate() : Boolean
}