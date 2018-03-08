from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode


class EqualsOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(EqualsOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)

    def evaluate(self):
        if self.left_expression.value and self.right_expression.value:
            self.value = self.left_expression.value == self.right_expression.value
