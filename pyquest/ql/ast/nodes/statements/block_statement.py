from ql.ast.nodes.base import BaseNode


class BlockStatementNode(BaseNode):
    def __init__(self, metadata, block):
        super(BlockStatementNode, self).__init__(metadata)
        self.__block = block

    @property
    def block(self):
        return self.__block
