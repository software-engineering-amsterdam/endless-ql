from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from AST.types.type_decimal import TypeDecimal
from AST.types.type_integer import TypeInteger
from AST.types.type_money import TypeMoney
from AST.types.type_string import TypeString
from AST.types.type_undefined import TypeUndefined


class SubtractionOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(SubtractionOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)
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
        if self.left_expression.value and self.right_expression.value:
            self.value = self.left_expression.value - self.right_expression.value
