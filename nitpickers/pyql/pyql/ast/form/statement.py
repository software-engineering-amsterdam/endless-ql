from pyql.ast import ast


class Statement(ast.ASTNode):

    def __init__(self, location):
        super().__init__(location)

    def accept(self, visitor):
        return visitor.visit_statement(self)

    def __repr__(self):
        return "AST Statement at: " + str(self.location)
