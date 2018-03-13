from AST.expressions.literals.literal_node import LiteralNode


class MoneyNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(MoneyNode, self).__init__(position, expression_type, round(value, 2))
