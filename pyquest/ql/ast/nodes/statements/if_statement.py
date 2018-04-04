from ql.ast.nodes.statements.block_statement import BlockStatementNode


class IfNode(BlockStatementNode):
    def __init__(self, metadata, block, condition):
        super(IfNode, self).__init__(metadata, block)
        self.__condition = condition

    @property
    def condition(self):
        return self.__condition
