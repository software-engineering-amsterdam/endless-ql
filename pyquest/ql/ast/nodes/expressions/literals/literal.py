from ql.ast.nodes.expressions.expression import ExpressionNode


class LiteralNode(ExpressionNode):
    def __init__(self, metadata, expression_type, value):
        super(LiteralNode, self).__init__(metadata, expression_type, value)

    @ExpressionNode.expression_type.setter
    def expression_type(self, unused):
        pass

    @ExpressionNode.value.setter
    def value(self, unused):
        pass
