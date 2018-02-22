from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode


class NegationOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, expression):
        super(NegationOperatorNode, self).__init__(position, expression_type, expression)

