from pyql.ast import expression_visitor
from pyql.static_analysis import symbol_table
from pyql.ast.expression import literals
from pyql.util import types


class ExpressionEvaluator(expression_visitor.ExpressionVisitor):

    def visit_expression(self, expression):
        print("Visiting expression")

    def visit_identifier(self, identifier):
        print("Visiting identifier")
        print(symbol_table.get(identifier))

    def visit_unary_expression(self, unary_expression):
        print("Visiting unary_expression")

    def visit_binary_expression(self, binary_expression):
        print("Visiting binary_expression")

    def visit_multiplication(self, multiplication):
        return self.visit_arithmetic_binary_expression(multiplication, "*")

    def visit_division(self, division):
        print("Visiting division")
        return self.visit_arithmetic_binary_expression(division, "/")

    def visit_addition(self, addition):
        print("Visiting addition")
        return self.visit_arithmetic_binary_expression(addition, "+")

    def visit_subtraction(self, subtraction):
        print("Visiting subtraction")
        return self.visit_arithmetic_binary_expression(subtraction, "-")

    def visit_greater_than(self, greater_than):
        print("Visiting greater_than")
        return self.visit_conditional_binary_expression(greater_than, ">")

    def visit_less_than(self, less_than):
        print("Visiting less_than")
        return self.visit_conditional_binary_expression(less_than, "<")

    def visit_greater_than_or_equal(self, greater_than_or_equal):
        print("Visiting greater_than_or_equal")
        return self.visit_conditional_binary_expression(greater_than_or_equal, ">=")

    def visit_less_than_or_equal(self, less_than_or_equal):
        print("Visiting less_than_or_equal")
        return self.visit_conditional_binary_expression(less_than_or_equal, "<=")

    def visit_equals(self, equals):
        print("Visiting equals")
        return self.visit_conditional_binary_expression(equals, "==")

    def visit_not_equals(self, not_equals):
        print("Visiting not_equals")
        return self.visit_conditional_binary_expression(not_equals, "!=")

    def visit_and(self, and_expression):
        print("Visiting and_expression")
        left = and_expression.left.accept(self)
        right = and_expression.right.accept(self)

        if left.type != types.Boolean and right.type != types.Boolean:
            raise TypeError("Can not compare non-boolean literals")

        return left and right

    def visit_or(self, or_expression):
        print("Visiting or_expression")
        left = or_expression.left.accept(self)
        right = or_expression.right.accept(self)

        if left.type != types.Boolean and right.type != types.Boolean:
            raise TypeError("Can not compare non-boolean literals")

        return left or right

    def visit_not(self, not_unary_expression):
        expression = not_unary_expression.expression.accept(self)
        print("Visiting not_unary_expression of ", expression)

        if not_unary_expression.type != types.Boolean:
            raise TypeError("Can not negate non-boolean literal")

        return not expression

    def visit_literal(self, literal):
        print("Visiting literal")

    def visit_string_literal(self, string_literal):
        print("Visiting string_literal")
        return string_literal

    def visit_integer_literal(self, integer_literal):
        print("Visiting integer_literal")
        return integer_literal

    def visit_decimal_literal(self, decimal_literal):
        print("Visiting decimal_literal")
        return decimal_literal

    def visit_boolean_literal(self, boolean_literal):
        print("Visiting boolean_literal")
        return boolean_literal

    def visit_money_literal(self, money_literal):
        print("Visiting money_literal")
        return money_literal

    def visit_arithmetic_binary_expression(self, expression, operator):
        left = expression.left.accept(self)
        right = expression.right.accept(self)
        print("Visiting arithmetic binary expression of ", left, operator, right)

        self.assert_valid_operand_types(operator, left.type, right.type)
        literal_class = self.determine_arithmetic_result_type(left.type, right.type)

        switcher = {
            "*": left.value * right.value,
            "/": left.value / right.value,
            "+": left.value + right.value,
            "-": left.value - right.value,
        }
        literal_value = switcher.get(operator)

        return literal_class(expression.location, literal_value)

    def visit_conditional_binary_expression(self, expression, operator):
        left = expression.left.accept(self)
        right = expression.right.accept(self)
        print("Visiting conditional binary expression of ", left, operator, right)

        self.assert_valid_operand_types(operator, left.type, right.type)

        switcher = {
            "<": left.value < right.value,
            ">": left.value > right.value,
            "<=": left.value <= right.value,
            ">=": left.value >= right.value,
            "==": left.value == right.value,
            "!=": left.value != right.value,
        }

        return literals.BooleanLiteral(expression.location, switcher.get(operator))

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
    def determine_arithmetic_result_type(left_type, right_type):
        string = types.String
        integer = types.Integer
        decimal = types.Decimal
        money = types.Money

        switcher = {
            (string, string): literals.StringLiteral,

            (integer, integer): literals.IntegerLiteral,
            (integer, decimal): literals.DecimalLiteral,
            (integer, money): literals.MoneyLiteral,

            (decimal, integer): literals.DecimalLiteral,
            (decimal, decimal): literals.DecimalLiteral,
            (decimal, money): literals.MoneyLiteral,

            (money, integer): literals.MoneyLiteral,
            (money, decimal): literals.MoneyLiteral,
            (money, money): literals.MoneyLiteral,
        }
        return_literal = switcher.get((left_type, right_type), False)
        if not return_literal:
            raise TypeError("Incompatible operand types")

        return return_literal
