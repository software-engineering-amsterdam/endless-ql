from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class OrOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(OrOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)
        self.__valid_types = {(QLBoolean, QLBoolean): QLBoolean}

    def get_result_type(self, type1, type2):
        if self.__valid_types.get((type1, type2)):
            return self.__valid_types.get((type1, type2))
        return QLUndefined

    def evaluate(self):
        self.value = self.expression_type(self.left_expression.value.value or self.right_expression.value.value)
