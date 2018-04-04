from ql.ast.nodes.expressions.literals.literal import LiteralNode


class UndefinedNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(UndefinedNode, self).__init__(metadata, expression_type, value)
