from ql.ast.expressions.literals.literal_node import LiteralNode


class StringNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(StringNode, self).__init__(position, expression_type, value)
