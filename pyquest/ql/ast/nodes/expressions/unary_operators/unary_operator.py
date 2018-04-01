from ql.ast.nodes.expressions.expression import ExpressionNode


class UnaryOperatorNode(ExpressionNode):
    def __init__(self, metadata, expression_type, expression, value):
        super(UnaryOperatorNode, self).__init__(metadata, expression_type, value)
        self.__expression = expression

    @property
    def expression(self):
        return self.__expression
