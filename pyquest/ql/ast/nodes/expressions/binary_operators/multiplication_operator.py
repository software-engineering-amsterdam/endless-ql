from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.undefined import QLUndefined


class MultiplicationOperatorNode(BinaryOperatorNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(MultiplicationOperatorNode, self).__init__(metadata, expression_type,
                                                         left_expression, right_expression, value)
        self.__valid_types = {
            (QLDecimal, QLDecimal): QLDecimal,
            (QLDecimal, QLInteger): QLDecimal,
            (QLDecimal, QLMoney):   QLMoney,
            (QLInteger, QLInteger): QLInteger,
            (QLInteger, QLDecimal): QLDecimal,
            (QLInteger, QLMoney):   QLMoney,
            (QLMoney, QLDecimal):   QLMoney,
            (QLMoney, QLInteger):   QLMoney,
        }

    def get_result_type(self):
        if self.__valid_types.get((self.left_expression.expression_type, self.right_expression.expression_type)):
            return self.__valid_types.get((self.left_expression.expression_type, self.right_expression.expression_type))

        return QLUndefined

    def evaluate(self):
        self.value = self.left_expression.value * self.right_expression.value
