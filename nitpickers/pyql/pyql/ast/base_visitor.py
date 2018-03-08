from abc import abstractmethod
from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.statement import Statement
from pyql.ast.form.block import Block
from pyql.ast.ast import ASTNode


class StatementVisitor:

    @abstractmethod
    @multimethod(Form)
    def visit(self, form):
        pass

    @abstractmethod
    @multimethod(Statement)
    def visit(self, statement):
        pass

    @abstractmethod
    @multimethod(Block)
    def visit(self, block):
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
