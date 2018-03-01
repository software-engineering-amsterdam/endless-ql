from AST.statements.block_statement_node import BlockStatementNode


class IfNode(BlockStatementNode):
    def __init__(self, position, block, condition):
        super(IfNode, self).__init__(position, block)
        self.__condition = condition

    @property
    def condition(self):
        return self.__condition

