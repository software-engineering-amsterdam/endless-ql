from pyql.ast.ast import ASTNode


class Statement(ASTNode):

    def __init__(self, location):
        super(Statement, self).__init__(location)

    def __repr__(self):
        return "AST Statement at: " + str(self.location)
