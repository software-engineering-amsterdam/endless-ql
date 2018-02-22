from src.AST.statements.block_statement_node import BlockStatementNode


class IfNode(BlockStatementNode):
    def __init__(self, position, block, condition):
        super(IfNode, self).__init__(position, block)
        self._condition = condition

    @property
    def condition(self):
        return self._condition
