from pyql.ast import ast


class Form(ast.ASTNode):

    def __init__(self, identifier, location, block):
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
        return str({"AST Form " + str(self.location): str(self.block)})
