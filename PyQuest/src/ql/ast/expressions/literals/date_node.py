from ql.ast.expressions.literals.literal_node import LiteralNode


class DateNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(DateNode, self).__init__(position, expression_type, value)
