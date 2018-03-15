from ql.ast.expressions.expression_node import ExpressionNode


class UnaryOperatorNode(ExpressionNode):
    def __init__(self, position, expression_type, expression, value):
        super(UnaryOperatorNode, self).__init__(position, expression_type, value)
        self.__expression = expression

    @property
    def expression(self):
        return self.__expression

