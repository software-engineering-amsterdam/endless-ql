from util.ast import ASTNode


class Page(ASTNode):

    def __init__(self, location, identifier, block):
        super().__init__(location)
        self._identifier = identifier
        self._block = block

    @property
    def identifier(self):
        return self._identifier

    @property
    def block(self):
        return self._block

    def __repr__(self):
        return "Page at {0}".format(self.location)

    __str__ = __repr__
