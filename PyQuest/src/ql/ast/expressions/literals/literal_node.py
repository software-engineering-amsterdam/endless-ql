from ql.ast.expressions.expression_node import ExpressionNode


class LiteralNode(ExpressionNode):
    def __init__(self, position, expression_type, value):
        super(LiteralNode, self).__init__(position, expression_type, value)

    @ExpressionNode.expression_type.setter
    def expression_type(self, value=None):
        pass

    @ExpressionNode.value.setter
    def value(self, value=None):
        pass
