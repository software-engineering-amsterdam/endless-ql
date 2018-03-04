from abc import abstractmethod


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
