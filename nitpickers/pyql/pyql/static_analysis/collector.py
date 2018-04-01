from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from util.ast import ASTNode
from util.multimethods import multimethod
from util.types import Type


class Collector:

    def __init__(self):
        self._dict = {}

    def collect_questions(self, tree):
        tree.accept(self)
        return self._dict

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        for x in block.statements:
            x.accept(self)

    @multimethod(Question)
    def visit(self, question):
        self._dict[question.identifier.identifier] = question

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.block.accept(self)

    @multimethod(Type)
    def visit(self, type):
        pass

    @multimethod(ASTNode)
    def visit(self, node):
        pass
