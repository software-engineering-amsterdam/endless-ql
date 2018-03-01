from AST.statements.block_statement_node import BlockStatementNode


class FormNode(BlockStatementNode):
    def __init__(self, position, block, label):
        super(FormNode, self).__init__(position, block)
        self.__label = label

    @property
    def label(self):
        return self.__label

