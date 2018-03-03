from AST.expressions.expression_node import ExpressionNode


class LiteralNode(ExpressionNode):
    def __init__(self, position, expression_type):
        super(LiteralNode, self).__init__(position, expression_type)
