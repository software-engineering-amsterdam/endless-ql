from ql.ast.nodes.expressions.literals.literal import LiteralNode


class BooleanNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(BooleanNode, self).__init__(metadata, expression_type, value)
