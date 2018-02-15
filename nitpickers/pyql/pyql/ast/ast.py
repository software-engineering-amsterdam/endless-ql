from pyql.ast import code_location


class ASTNode:

    def __init__(self, location: code_location.CodeLocation):
        self._location = location

    @property
    def location(self):
        return self._location

    def __repr__(self):
        return "AST node at: " + str(self._location)
