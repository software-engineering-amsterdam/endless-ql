class ASTNode:

    def __init__(self, location):
        self._location = location

    @property
    def location(self):
        return self._location

    def __repr__(self):
        return str({"AST node " + str(self.location)})

    def accept(self, visitor):
        return visitor.visit(self)
