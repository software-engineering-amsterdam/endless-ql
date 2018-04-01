from ql.ast.nodes.expressions.literals.literal import LiteralNode


class MoneyNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(MoneyNode, self).__init__(metadata, expression_type, value)
