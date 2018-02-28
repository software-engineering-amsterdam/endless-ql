from abc import abstractmethod
from pyql.ast.expression import expressions
from pyql.ast.static_analysis import symbol_table


class ExpressionVisitor:

    @abstractmethod
    def visit_expression(self, expression):
        pass

    @abstractmethod
    def visit_identifier(self, identifier):
        pass

    @abstractmethod
    def visit_unary_expression(self, unary_expression):
        pass

    @abstractmethod
    def visit_binary_expression(self, binary_expression):
        pass

    @abstractmethod
    def visit_multiplication(self, multiplication):
        pass

    @abstractmethod
    def visit_division(self, division):
        pass

    @abstractmethod
    def visit_addition(self, addition):
        pass

    @abstractmethod
    def visit_subtraction(self, subtraction):
        pass

    @abstractmethod
    def visit_greater_than(self, greater_than):
        pass

    @abstractmethod
    def visit_less_than(self, less_than):
        pass

    @abstractmethod
    def visit_greater_than_or_equal(self, greater_than_or_equal):
        pass

    @abstractmethod
    def visit_less_than_or_equal(self, less_than_or_equal):
        pass

    @abstractmethod
    def visit_equals(self, equals):
        pass

    @abstractmethod
    def visit_not_equals(self, not_equals):
        pass

    @abstractmethod
    def visit_and(self, and_expression):
        pass

    @abstractmethod
    def visit_or(self, or_expression):
        pass

    @abstractmethod
    def visit_not(self, not_unary_expression):
        pass

    @abstractmethod
    def visit_literal(self, literal):
        pass

    @abstractmethod
    def visit_string_literal(self, string_literal):
        pass

    @abstractmethod
    def visit_integer_literal(self, int_literal):
        pass

    @abstractmethod
    def visit_decimal_literal(self, decimal_literal):
        pass

    @abstractmethod
    def visit_boolean_literal(self, bool_literal):
        pass

    @abstractmethod
    def visit_money_literal(self, money_literal):
        pass


class ExpressionEvaluator(ExpressionVisitor):

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
        left = multiplication.left.accept(self)
        right = multiplication.right.accept(self)
        print("Visiting multiplication of ", left, right)
        return left * right

    def visit_division(self, division):
        left = division.left.accept(self)
        right = division.right.accept(self)
        print("Visiting division of ", left, right)
        return left / right

    def visit_addition(self, addition):
        left = addition.left.accept(self)
        right = addition.right.accept(self)
        print("Visiting addition of ", left, right)
        return left + right

    def visit_subtraction(self, subtraction):
        left = subtraction.left.accept(self)
        right = subtraction.right.accept(self)
        print("Visiting subtraction of ", left, right)
        return left - right

    def visit_greater_than(self, greater_than):
        left = greater_than.left.accept(self)
        right = greater_than.right.accept(self)
        print("Visiting greater_than of ", left, right)
        return left > right

    def visit_less_than(self, less_than):
        left = less_than.left.accept(self)
        right = less_than.right.accept(self)
        print("Visiting less_than of ", left, right)
        return left < right

    def visit_greater_than_or_equal(self, greater_than_or_equal):
        left = greater_than_or_equal.left.accept(self)
        right = greater_than_or_equal.right.accept(self)
        print("Visiting greater_than_or_equal of ", left, right)
        return left >= right

    def visit_less_than_or_equal(self, less_than_or_equal):
        left = less_than_or_equal.left.accept(self)
        right = less_than_or_equal.right.accept(self)
        print("Visiting less_than_or_equal of ", left, right)
        return left <= right

    def visit_equals(self, equals):
        left = equals.left.accept(self)
        right = equals.right.accept(self)
        print("Visiting equals of ", left, right)
        return left.equals(right)

    def visit_not_equals(self, not_equals):
        left = not_equals.left.accept(self)
        right = not_equals.right.accept(self)
        print("Visiting not_equals of ", left, right)
        return self.visit_not(left.equals(right))

    def visit_and(self, and_expression):
        left = and_expression.left.accept(self)
        right = and_expression.right.accept(self)
        print("Visiting and_expression of ", left, right)
        return left and right

    def visit_or(self, or_expression):
        left = or_expression.left.accept(self)
        right = or_expression.right.accept(self)
        print("Visiting or_expression of ", left, right)
        return left or right

    def visit_not(self, not_unary_expression):
        expression = not_unary_expression.expression.accept(self)
        print("Visiting not_unary_expression of ", expression)
        return not expression

    def visit_literal(self, literal):
        print("Visiting literal")

    def visit_string_literal(self, string_literal):
        print("Visiting string_literal")
        return string_literal.value

    def visit_integer_literal(self, integer_literal):
        print("Visiting integer_literal")
        return integer_literal.value

    def visit_decimal_literal(self, decimal_literal):
        print("Visiting decimal_literal")
        return decimal_literal.value

    def visit_boolean_literal(self, boolean_literal):
        print("Visiting boolean_literal")
        return boolean_literal.value

    def visit_money_literal(self, money_literal):
        print("Visiting money_literal")
        return money_literal.value
