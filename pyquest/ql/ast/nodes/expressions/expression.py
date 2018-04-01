from ql.ast.nodes.base import BaseNode


class ExpressionNode(BaseNode):
    def __init__(self, metadata, expression_type, value):
        super(ExpressionNode, self).__init__(metadata)
        self.__expression_type = expression_type
        self.__value = value

    @property
    def expression_type(self):
        return self.__expression_type

    @expression_type.setter
    def expression_type(self, value):
        self.__expression_type = value

    @property
    def value(self):
        return self.__value

    @value.setter
    def value(self, value):
        self.__value = value
