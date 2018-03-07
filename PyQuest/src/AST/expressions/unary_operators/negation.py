from AST.expressions.unary_operators.unary_operator import UnaryOperatorNode


class NegationOperatorNode(UnaryOperatorNode):
    def __init__(self, position, expression_type, expression, value):
        super(NegationOperatorNode, self).__init__(position, expression_type, expression, value)

