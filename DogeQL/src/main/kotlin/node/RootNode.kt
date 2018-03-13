package node

class RootNode : Node() {
    override fun validate(): Boolean = children.all {
        child -> child.validate()
    }
}