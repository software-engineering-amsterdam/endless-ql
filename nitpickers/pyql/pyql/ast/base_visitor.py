from abc import abstractmethod


class StatementVisitor:

    @abstractmethod
    def visit_form(self, form):
        pass

    @abstractmethod
    def visit_block(self, block):
        pass

    @abstractmethod
    def visit_statement(self, statement):
        pass

    @abstractmethod
    def visit_question(self, question):
        pass

    @abstractmethod
    def visit_computed_question(self, question):
        pass

    @abstractmethod
    def visit_if(self, if_statement):
        pass

    @abstractmethod
    def visit_if_else(self, if_else_statement):
        pass

    @abstractmethod
    def visit_ast_node(self, node):
        pass

    @abstractmethod
    def visit_identifier(self, identifier):
        pass

    @abstractmethod
    def visit_question_type(self, question_type):
        pass


class ExpressionVisitor:

    @abstractmethod
    def visit_expression(self, expression):
        pass

    @abstractmethod
    def visit_identifier(self, expression):
        pass

    @abstractmethod
    def visit_unary_expression(self, expression):
        pass

    @abstractmethod
    def visit_binary_expression(self, expression):
        pass

    @abstractmethod
    def visit_multiplication(self, expression):
        pass

    @abstractmethod
    def visit_division(self, expression):
        pass

    @abstractmethod
    def visit_addition(self, expression):
        pass

    @abstractmethod
    def visit_subtraction(self, expression):
        pass

    @abstractmethod
    def visit_greater_than(self, expression):
        pass

    @abstractmethod
    def visit_less_than(self, expression):
        pass

    @abstractmethod
    def visit_greater_than_or_equal(self, expression):
        pass

    @abstractmethod
    def visit_less_than_or_equal(self, expression):
        pass

    @abstractmethod
    def visit_equals(self, expression):
        pass

    @abstractmethod
    def visit_not_equals(self, expression):
        pass

    @abstractmethod
    def visit_and(self, expression):
        pass

    @abstractmethod
    def visit_or(self, expression):
        pass

    @abstractmethod
    def visit_not(self, unary_expression):
        pass

    @abstractmethod
    def visit_literal(self, literal):
        pass

    @abstractmethod
    def visit_string_literal(self, literal):
        pass

    @abstractmethod
    def visit_int_literal(self, literal):
        pass

    @abstractmethod
    def visit_decimal_literal(self, literal):
        pass

    @abstractmethod
    def visit_bool_literal(self, literal):
        pass

    @abstractmethod
    def visit_money_literal(self, literal):
        pass
