from src.AST.expressions.expression_node import ExpressionNode


class DecimalNode(ExpressionNode):
    def __init__(self, position, expression_type, value):
        super(DecimalNode, self).__init__(position, expression_type)
        self._value = value

    @property
    def value(self):
        return self._value
