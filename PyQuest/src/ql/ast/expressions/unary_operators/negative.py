from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode


class NegativeOperatorNode(UnaryOperatorNode):
    def __init__(self, position, expression_type, expression, value):
        super(NegativeOperatorNode, self).__init__(position, expression_type, expression, value)

    def evaluate(self):
        if self.expression.value is not None:
            self.value = - self.expression.value

