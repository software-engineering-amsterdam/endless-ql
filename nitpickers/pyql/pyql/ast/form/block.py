from pyql.ast.ast_node import ASTNode


class Block(ASTNode):

    def __init__(self, location, statements: []):
        super(Block, self).__init__(location)
        self._statements = statements

    @property
    def statements(self):
        return self._statements

    def __repr__(self):
        return "AST Block at: " + str(self.location)

