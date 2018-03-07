from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import ComputedQuestion
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from pyql.ast.ast import ASTNode
from pyql.ast.expression.expressions import Identifier
from pyql.util.types import Type

from pyql.static_analysis.expression_visitor import ExpressionVisitor


class TypeChecker:

    def __init__(self):
        self._expression_visitor = ExpressionVisitor()

    @multimethod(Form)
    def visit(self, form):
        print("Visiting form")
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        print("Visiting block")
        questions = block.statements
        for q in questions:
            q.accept(self)

    @multimethod(Question)
    def visit(self, question):
        print("Visiting questions")
        print(question.identifier)
        question.identifier.accept(self)
        question.question_type.accept(self)

    @multimethod(ComputedQuestion)
    def visit(self, question):
        print("Visiting computed questions")
        print(question.identifier)

    @multimethod(If)
    def visit(self, if_statement):
        print("Visiting if statement")
        if_statement.block.accept(self)
        if_statement.expression.accept(self._expression_visitor)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        print("Visiting if else statement")
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)
        if_else_statement.expression.accept(self._expression_visitor)

    @multimethod(Identifier)
    def visit(self, identifier):
        print("Visiting identifier {0}".format(identifier))

    @multimethod(Type)
    def visit(self, type):
        print("Type: {0}".format(type))

    @multimethod(ASTNode)
    def visit(self, node):
        print("ASTNode: {0}".format(node))
