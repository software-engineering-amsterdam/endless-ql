from ql.ast.expressions.literals.literal_node import LiteralNode


class MoneyNode(LiteralNode):
    def __init__(self, metadata, expression_type, value):
        super(MoneyNode, self).__init__(metadata, expression_type, value)
