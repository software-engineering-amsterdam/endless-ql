from multimethods import multimethod
from pyql.ast.expression.expressions import *
from util import types


class ExpressionEvaluator:

    def __init__(self, symbol_table):
        self.symbol_table = symbol_table

    @multimethod(Identifier)
    def visit(self, identifier):
        return self.symbol_table.get(identifier.identifier)

    # @multimethod(MoneyLiteral)
    # def visit(self, money_literal):
    #     return money_literal
    #
    # @multimethod(DecimalLiteral)
    # def visit(self, decimal_literal):
    #     return decimal_literal
    #
    # @multimethod(IntegerLiteral)
    # def visit(self, integer_literal):
    #     return integer_literal
    #
    # @multimethod(BooleanLiteral)
    # def visit(self, boolean_literal):
    #     return boolean_literal
    #
    # @multimethod(StringLiteral)
    # def visit(self, string_literal):
    #     return string_literal

    @multimethod(Literal)
    def visit(self, literal):
        return literal.value

    @multimethod(Multiplication)
    def visit(self, multiplication):
        return multiplication.left.accept(self) * multiplication.right.accept(self)

    @multimethod(Division)
    def visit(self, division):
        return division.left.accept(self) / division.right.accept(self)

    @multimethod(Addition)
    def visit(self, addition):
        return addition.left.accept(self) + addition.right.accept(self)

    @multimethod(Subtraction)
    def visit(self, subtraction):
        return subtraction.left.accept(self) - subtraction.right.accept(self)

    @multimethod(GreaterThan)
    def visit(self, greater_than):
        return greater_than.left.accept(self) > greater_than.right.accept(self)

    @multimethod(LessThan)
    def visit(self, less_than):
        return less_than.left.accept(self) < less_than.right.accept(self)

    @multimethod(GreaterThanOrEqual)
    def visit(self, greater_than_or_equal):
        return greater_than_or_equal.left.accept(self) >= greater_than_or_equal.right.accept(self)

    @multimethod(LessThanOrEqual)
    def visit(self, less_than_or_equal):
        return less_than_or_equal.left.accept(self) <= less_than_or_equal.right.accept(self)

    @multimethod(Equals)
    def visit(self, equals):
        return equals.left.accept(self) == equals.right.accept(self)

    @multimethod(NotEquals)
    def visit(self, not_equals):
        return not_equals.left.accept(self) != not_equals.right.accept(self)

    # @multimethod(And)
    # def visit(self, and_expression):
    #     left = and_expression.left.accept(self)
    #     right = and_expression.right.accept(self)
    #
    #     if left.type != types.Boolean and right.type != types.Boolean:
    #         raise TypeError("Can not compare non-boolean literals")
    #
    #     return left and right
    #
    # @multimethod(Or)
    # def visit(self, or_expression):
    #     left = or_expression.left.accept(self)
    #     right = or_expression.right.accept(self)
    #
    #     if left.type != types.Boolean and right.type != types.Boolean:
    #         raise TypeError("Can not compare non-boolean literals")
    #
    #     return left or right

    @multimethod(Not)
    def visit(self, not_unary_expression):
        return not not_unary_expression.left.accept(self)
        # expression = not_unary_expression.expression.accept(self)
        # print("Visiting not_unary_expression of ", expression)
        #
        # if not_unary_expression.type != types.Boolean:
        #     raise TypeError("Can not negate non-boolean literal")
        #
        # return not expression

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        pass

    @multimethod(BinaryExpression)
    def visit(self, binary_expression):
        pass

    @multimethod(Expression)
    def visit(self, expression):
        pass

    # left = expression.left.accept(self)
    # right = expression.right.accept(self)
    # self.assert_valid_operand_types(operator, left.type, right.type)
    #
    # literal_class = self.infer_result_literal(left.type, right.type)
    # result_value = left.value * right.value
    # return literal_class(expression.location, result_value)

    # def evaluate_binary_expression(self, expression, operator):
    #     left = expression.left.accept(self)
    #     right = expression.right.accept(self)
    #
    #     self.assert_valid_operand_types(operator, left.type, right.type)
    #
    #     if operator in ["*", "/", "+", "-"]:
    #         literal_class = self.infer_result_literal(left.type, right.type)
    #     else:
    #         literal_class = BooleanLiteral
    #
    #     switcher = {
    #         "*": lambda: left.value * right.value,
    #         "/": lambda: left.value / right.value,
    #         "+": lambda: left.value + right.value,
    #         "-": lambda: left.value - right.value,
    #         "<": lambda: left.value < right.value,
    #         ">": lambda: left.value > right.value,
    #         "<=": lambda: left.value <= right.value,
    #         ">=": lambda: left.value >= right.value,
    #         "==": lambda: left.value == right.value,
    #         "!=": lambda: left.value != right.value,
    #     }
    #     literal_value = switcher.get(operator)()
    #
    #     return literal_class(expression.location, literal_value)

    @staticmethod
    def assert_valid_operand_types(operator, left_type, right_type):
        boolean = types.Boolean
        string = types.String
        integer = types.Integer
        decimal = types.Decimal
        money = types.Money

        switcher = {
            (boolean, boolean): ["==", "!="],

            (string, string): ["+", "==", "!="],

            (integer, integer): ["*", "/", "+", "-", "<", ">", "<=", ">=", "==", "!="],
            (integer, decimal): ["*", "/", "+", "-", "<", ">", "<=", ">=", "==", "!="],
            (integer, money): ["*"],

            (decimal, integer): ["*", "/", "+", "-", "<", ">", "<=", ">=", "==", "!="],
            (decimal, decimal): ["*", "/", "+", "-", "<", ">", "<=", ">=", "==", "!="],
            (decimal, money): ["*"],

            (money, integer): ["*", "/"],
            (money, decimal): ["*", "/"],
            (money, money): ["+", "-", "<", ">", "<=", ">=", "==", "!="],
        }
        operator_is_valid = operator in switcher.get((left_type, right_type), [])
        if not operator_is_valid:
            raise TypeError("Invalid operand types for operation")

    @staticmethod
    def infer_result_literal(left_type, right_type):
        string = types.String
        integer = types.Integer
        decimal = types.Decimal
        money = types.Money

        switcher = {
            (string, string): StringLiteral,

            (integer, integer): IntegerLiteral,
            (integer, decimal): DecimalLiteral,
            (integer, money): MoneyLiteral,

            (decimal, integer): DecimalLiteral,
            (decimal, decimal): DecimalLiteral,
            (decimal, money): MoneyLiteral,

            (money, integer): MoneyLiteral,
            (money, decimal): MoneyLiteral,
            (money, money): MoneyLiteral,
        }
        return_literal = switcher.get((left_type, right_type), False)
        if not return_literal:
            raise TypeError("Incompatible operand types")

        return return_literal
