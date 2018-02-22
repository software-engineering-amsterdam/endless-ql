from AST.expressions.expression_node import ExpressionNode


class BinaryOperatorNode(ExpressionNode):
    def __init__(self, position, expression_type, left_expression, right_expression):
        super(BinaryOperatorNode, self).__init__(position, expression_type)
        self._left_expression = left_expression
        self._right_expression = right_expression

    @property
    def left_expression(self):
        return self._left_expression

    @property
    def right_expression(self):
        return self._right_expression
