from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class NegationOperatorNode(UnaryOperatorNode):
    def __init__(self, position, expression_type, expression, value):
        super(NegationOperatorNode, self).__init__(position, expression_type, expression, value)
        self.__valid_types = {QLBoolean: QLBoolean}

    def get_result_type(self, type):
        if self.__valid_types.get(type):
            return self.__valid_types.get(type)
        return QLUndefined

    def evaluate(self):
        self.value = self.expression_type(not self.expression.value.value)
