class ASTNode:

    def __init__(self, location):
        self._location = location

    @property
    def location(self):
        return self._location

    def __repr__(self):
        return "AST Node: {0}".format(self.location)

    def accept(self, visitor):
        return visitor.visit(self)

    def __str__(self):
        return self.__repr__()
