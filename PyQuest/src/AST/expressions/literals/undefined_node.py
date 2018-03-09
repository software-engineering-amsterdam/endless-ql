from AST.expressions.literals.literal_node import LiteralNode


class UndefinedNode(LiteralNode):
    def __init__(self, position, expression_type, value):
        super(UndefinedNode, self).__init__(position, expression_type, value)
