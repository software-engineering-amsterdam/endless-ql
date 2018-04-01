from util.ast import ASTNode


class Block(ASTNode):

    def __init__(self, location, statements: []):
        super().__init__(location)
        self._statements = statements

    @property
    def statements(self):
        return self._statements

    def __repr__(self):
        return "Block at {0}".format(self.location)

    __str__ = __repr__
