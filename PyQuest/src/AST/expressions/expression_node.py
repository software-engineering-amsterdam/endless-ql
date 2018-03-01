from AST.base_node import BaseNode


class ExpressionNode(BaseNode):
    def __init__(self, position, expression_type):
        super(ExpressionNode, self).__init__(position)
        self.__expression_type = expression_type

    @property
    def expression_type(self):
        return self.__expression_type

