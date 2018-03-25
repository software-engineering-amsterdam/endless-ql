from ql.ast.expressions.expression_node import ExpressionNode


class VariableNode(ExpressionNode):
    def __init__(self, metadata, expression_type, identifier, value):
        super(VariableNode, self).__init__(metadata, expression_type, value)
        self.__identifier = identifier

    @property
    def identifier(self):
        return self.__identifier
