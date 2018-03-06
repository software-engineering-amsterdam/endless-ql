from AST.base_node import BaseNode


class BlockStatementNode(BaseNode):
    def __init__(self, position, block):
        super(BlockStatementNode, self).__init__(position)
        self.__block = block

    @property
    def block(self):
        return self.__block
