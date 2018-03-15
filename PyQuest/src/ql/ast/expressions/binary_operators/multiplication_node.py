from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.undefined import QLUndefined


class MultiplicationOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(MultiplicationOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)
        self.__valid_types = {(QLDecimal, QLDecimal): QLDecimal,
                              (QLDecimal, QLInteger): QLDecimal,
                              (QLDecimal, QLMoney):   QLMoney,
                              (QLInteger, QLInteger): QLInteger,
                              (QLInteger, QLDecimal): QLDecimal,
                              (QLInteger, QLMoney):   QLMoney,
                              (QLMoney, QLDecimal): QLMoney,
                              (QLMoney, QLInteger): QLMoney}

    def get_result_type(self, type1, type2):
        if self.__valid_types.get((type1, type2)):
            return self.__valid_types.get((type1, type2))
        return QLUndefined

    def evaluate(self):
        if self.left_expression.value is not None and self.right_expression.value is not None:
            self.value = self.left_expression.value * self.right_expression.value
