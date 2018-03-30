from ql.ast.nodes.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.types.undefined import QLUndefined
from ql.types.boolean import QLBoolean
from ql.types.money import QLMoney
from ql.types.decimal import QLDecimal
from ql.types.integer import QLInteger
from ql.types.date import QLDate


class LessThanOperatorNode(BinaryOperatorNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(LessThanOperatorNode, self).__init__(metadata, expression_type, left_expression, right_expression, value)
        self.__valid_types = [(QLDate, QLDate),
                              (QLDecimal, QLDecimal),
                              (QLDecimal, QLInteger),
                              (QLDecimal, QLMoney),
                              (QLInteger, QLInteger),
                              (QLInteger, QLMoney),
                              (QLMoney, QLMoney)]

    def get_result_type(self, type1, type2):
        if (type1, type2) in self.__valid_types or (type2, type1) in self.__valid_types:
            return QLBoolean
        return QLUndefined

    def evaluate(self):
        self.value = self.expression_type(self.left_expression.value < self.right_expression.value)
