from ql.ast.nodes.expressions.unary_operators.unary_operator_node import UnaryOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class NegationOperatorNode(UnaryOperatorNode):
    def __init__(self, metadata, expression_type, expression, value):
        super(NegationOperatorNode, self).__init__(metadata, expression_type, expression, value)
        self.__valid_types = {QLBoolean: QLBoolean}

    def get_result_type(self, type):
        if self.__valid_types.get(type):
            return self.__valid_types.get(type)
        return QLUndefined

    def evaluate(self):
        self.value = self.expression_type(not self.expression.value.value)
