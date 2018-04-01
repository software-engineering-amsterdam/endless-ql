from ql.ast.nodes.expressions.literals.literal import LiteralNode


class StringNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(StringNode, self).__init__(metadata, expression_type, value)
