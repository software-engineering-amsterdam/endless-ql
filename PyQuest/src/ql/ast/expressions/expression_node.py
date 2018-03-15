from ql.ast.base_node import BaseNode


class ExpressionNode(BaseNode):
    def __init__(self, position, expression_type, value):
        super(ExpressionNode, self).__init__(position)
        self.__expression_type = expression_type
        self.__value = value

    @property
    def expression_type(self):
        return self.__expression_type

    def set_expression_type(self, expression_type):
        self.__expression_type = expression_type

    @property
    def value(self):
        return self.__value

    @value.setter
    def value(self, value):
        self.__value = value
