from ql.ast.expressions.expression_node import ExpressionNode


class VariableNode(ExpressionNode):
    def __init__(self, position, expression_type, identifier, value):
        super(VariableNode, self).__init__(position, expression_type, value)
        self.__identifier = identifier

    @property
    def identifier(self):
        return self.__identifier

    @property
    def value(self):
        return self.__value

    @value.setter
    def value(self, value):
        self.__value = value
