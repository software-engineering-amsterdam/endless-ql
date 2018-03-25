from ql.ast.statements.block_statement_node import BlockStatementNode


class FormNode(BlockStatementNode):
    def __init__(self, metadata, block, identifier):
        super(FormNode, self).__init__(metadata, block)
        self.__identifier = identifier

    @property
    def identifier(self):
        return self.__identifier

