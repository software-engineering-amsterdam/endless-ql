from AST.statements.block_statement_node import BlockStatementNode


class FormNode(BlockStatementNode):
    def __init__(self, position, block, identifier):
        super(FormNode, self).__init__(position, block)
        self.__identifier = identifier

    @property
    def identifier(self):
        return self.__identifier

