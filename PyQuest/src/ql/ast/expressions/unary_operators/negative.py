from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.undefined import QLUndefined


class NegativeOperatorNode(UnaryOperatorNode):
    def __init__(self, position, expression_type, expression, value):
        super(NegativeOperatorNode, self).__init__(position, expression_type, expression, value)
        self.__valid_types = {QLDecimal: QLDecimal,
                              QLInteger: QLInteger,
                              QLMoney: QLMoney}

    def get_result_type(self, type):
        if self.__valid_types.get(type):
            return self.__valid_types.get(type)
        return QLUndefined

    def evaluate(self):
        self.value = self.expression_type(- self.expression.value.value)


