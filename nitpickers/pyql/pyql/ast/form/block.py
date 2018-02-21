from pyql.ast.ast import ASTNode


class Block(ASTNode):

    def __init__(self, location, statements: []):
        super().__init__(location)
        self._statements = statements

    @property
    def statements(self):
        return self._statements

    def __repr__(self):
        return str({"AST Block " + str(self.location): str(self.statements)})

