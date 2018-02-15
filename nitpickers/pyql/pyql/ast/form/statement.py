from pyql.ast.ast_node import ASTNode


class Statement(ASTNode):

    def __init__(self, loc=None):
        super(Statement, self).__init__(loc)

    def __repr__(self):
        return "AST statement at: " + str(self.location)