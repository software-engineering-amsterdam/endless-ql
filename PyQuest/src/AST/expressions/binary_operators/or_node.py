from src.AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode


class OrOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression):
        super(OrOperatorNode, self).__init__(position, expression_type, left_expression, right_expression)

