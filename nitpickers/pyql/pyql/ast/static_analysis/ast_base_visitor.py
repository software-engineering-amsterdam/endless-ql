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
