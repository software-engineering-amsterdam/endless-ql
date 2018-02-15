from pyql.ast.form.statement import Statement
from pyql.ast.form.block import Block
from pyql.ast.expression import Expression


class IfStatement(Statement):

    def __init__(self, location, expression: Expression, block: Block):
        super(IfStatement, self).__init__(location)
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
