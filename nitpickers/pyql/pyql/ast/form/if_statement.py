from pyql.ast.form import statement
from pyql.ast.form import block
from pyql.ast.expression import expressions


class IfStatement(statement.Statement):

    def __init__(self, location, expression: expressions.Expression, block: block.Block):
        super().__init__(location)
        self._expression = expression
        self._block = block

    @property
    def expression(self):
        return self._expression

    @property
    def block(self):
        return self._block

    def __repr__(self):
        return "AST IfStatement at: " + str(self.location)
