from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from AST.types.type_boolean import TypeBoolean
from AST.types.type_date import TypeDate
from AST.types.type_decimal import TypeDecimal
from AST.types.type_integer import TypeInteger
from AST.types.type_money import TypeMoney
from AST.types.type_string import TypeString


class NotEqualsOperatorNode(BinaryOperatorNode):
    def __init__(self, position, expression_type, left_expression, right_expression, value):
        super(NotEqualsOperatorNode, self).__init__(position, expression_type, left_expression, right_expression, value)
        self.__valid_types = [(TypeBoolean, TypeBoolean),
                              (TypeDate, TypeDate),
                              (TypeDecimal, TypeDecimal),
                              (TypeDecimal, TypeInteger),
                              (TypeDecimal, TypeMoney),
                              (TypeInteger, TypeInteger),
                              (TypeInteger, TypeMoney),
                              (TypeMoney, TypeMoney),
                              (TypeString, TypeString)]

    def valid_operation(self, type1, type2):
        if (type1, type2) or (type2, type1) in self.__valid_types:
            return True
        return False
