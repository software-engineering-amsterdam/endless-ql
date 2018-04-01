from ql.ast.nodes.expressions.expression import ExpressionNode


class BinaryOperatorNode(ExpressionNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(BinaryOperatorNode, self).__init__(metadata, expression_type, value)
        self.__left_expression = left_expression
        self.__right_expression = right_expression

    @property
    def left_expression(self):
        return self.__left_expression

    @property
    def right_expression(self):
        return self.__right_expression

