from src.AST.expressions.binary_operators.binary_operator import BinaryOperatorNode


class NegationOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, expression):
        super(NegationOperatorNode, self).__init__(position, expression_type, expression)

