from ql.ast.nodes.expressions.literals.literal import LiteralNode


class DecimalNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(DecimalNode, self).__init__(metadata, expression_type, value)
