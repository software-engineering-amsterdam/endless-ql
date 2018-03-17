from ql.ast.expressions.literals.literal_node import LiteralNode


class BooleanNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(BooleanNode, self).__init__(position, expression_type, value)
