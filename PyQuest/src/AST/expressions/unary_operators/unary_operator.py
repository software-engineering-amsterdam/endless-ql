from src.AST.expressions.expression import ExpressionNode


class UnaryOperatorNode(ExpressionNode):
    def __init__(self, position, expression_type, expression):
        super(UnaryOperatorNode, self).__init__(position, expression_type)
        self._expression = expression

    @property
    def expression(self):
        return self._expression
