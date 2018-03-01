from AST.expressions.expression_node import ExpressionNode


class VariableNode(ExpressionNode):
    def __init__(self, position, expression_type, identifier):
        super(VariableNode, self).__init__(position, expression_type)
        self.__identifier = identifier

    @property
    def identifier(self):
        return self.__identifier

