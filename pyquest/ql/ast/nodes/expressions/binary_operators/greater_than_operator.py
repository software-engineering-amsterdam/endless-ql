from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.date import QLDate
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.money import QLMoney
from ql.types.undefined import QLUndefined


class GreaterThanOperatorNode(BinaryOperatorNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(GreaterThanOperatorNode, self).__init__(metadata, expression_type,
                                                      left_expression, right_expression, value)
        self.__valid_types = [
            (QLDate,    QLDate),
            (QLDecimal, QLDecimal),
            (QLDecimal, QLInteger),
            (QLInteger, QLInteger),
            (QLInteger, QLDecimal),
            (QLMoney,   QLMoney),
        ]

    def get_result_type(self):
        if (self.left_expression.expression_type, self.right_expression.expression_type) in self.__valid_types:
            return QLBoolean

        return QLUndefined

    def evaluate(self):
        self.value = self.left_expression.value > self.right_expression.value
