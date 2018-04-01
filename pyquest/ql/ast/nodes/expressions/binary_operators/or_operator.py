from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class OrOperatorNode(BinaryOperatorNode):
    def __init__(self, metadata, expression_type, left_expression, right_expression, value):
        super(OrOperatorNode, self).__init__(metadata, expression_type,
                                             left_expression, right_expression, value)
        self.__valid_types = {
            (QLBoolean, QLBoolean): QLBoolean,
        }

    def get_result_type(self):
        if self.__valid_types.get((self.left_expression.expression_type, self.right_expression.expression_type)):
            return self.__valid_types.get((self.left_expression.expression_type, self.right_expression.expression_type))

        return QLUndefined

    def evaluate(self):
        self.value = self.left_expression.value or self.right_expression.value
