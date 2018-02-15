from pyql.ast import ast


class Form(ast.ASTNode):

    def __init__(self, location, block):
        self._location = location
        self._block = block

    @property
    def block(self):
        return self._block

    def __repr__(self):
        return str({"AST Form " + str(self.location): str(self.block)})
