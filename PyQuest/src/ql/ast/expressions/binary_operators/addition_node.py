from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.types.type_decimal import TypeDecimal
from ql.types.type_integer import TypeInteger
from ql.types.type_money import TypeMoney
from ql.types.type_string import TypeString
from ql.types.type_undefined import TypeUndefined


class AdditionOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(AdditionOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)
        self.__valid_types = {(TypeDecimal, TypeDecimal): TypeDecimal,
                              (TypeDecimal, TypeInteger): TypeDecimal,
                              (TypeDecimal, TypeMoney):   TypeMoney,
                              (TypeInteger, TypeInteger): TypeInteger,
                              (TypeInteger, TypeDecimal): TypeDecimal,
                              (TypeInteger, TypeMoney):   TypeMoney,
                              (TypeMoney,   TypeMoney):   TypeMoney,
                              (TypeMoney,   TypeDecimal): TypeMoney,
                              (TypeMoney,   TypeInteger): TypeMoney,
                              (TypeString,  TypeString):  TypeString}

    def get_result_type(self, type1, type2):
        if self.__valid_types.get((type1, type2)):
            return self.__valid_types.get((type1, type2))
        return TypeUndefined

    def evaluate(self):
        result_type = self.get_result_type(self.left_expression.expression_type, self.right_expression.expression_type)

        if self.left_expression.value is not None and self.right_expression.value is not None:
            self.value = result_type.cast(self.left_expression.value + self.right_expression.value)
