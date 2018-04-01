from ql.ast.nodes.expressions.literals.literal import LiteralNode


class DateNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(DateNode, self).__init__(metadata, expression_type, value)
