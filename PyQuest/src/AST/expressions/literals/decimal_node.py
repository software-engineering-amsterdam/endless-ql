from AST.expressions.literals.literal_node import LiteralNode


class DecimalNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(DecimalNode, self).__init__(position, expression_type)
        self._value = value

    @property
    def value(self):
        return self._value
