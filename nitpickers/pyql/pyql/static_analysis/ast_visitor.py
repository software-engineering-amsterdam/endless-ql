from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import ComputedQuestion
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from pyql.ast.ast import ASTNode
from pyql.ast.expression.expressions import Identifier
from pyql.util.types import Type
from pyql.static_analysis.expression_visitor import ExpressionVisitor


class TypeCheckVisitor:

    def __init__(self, symbol_table):
        self._expression_visitor = ExpressionVisitor(symbol_table)

    @multimethod(Identifier)
    def visit(self, identifier):
        pass

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        questions = block.statements
        for q in questions:
            q.accept(self)

    @multimethod(ComputedQuestion)
    def visit(self, computed_question):
        computed_question.identifier.accept(self)
        computed_question.question_type.accept(self)
        # computed_question.expression.accept(self._expression_visitor)

    @multimethod(Question)
    def visit(self, question):
        question.identifier.accept(self)
        question.question_type.accept(self)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.block.accept(self)
        # if_statement.expression.accept(self._expression_visitor)

    @multimethod(Type)
    def visit(self, type):
        pass

    @multimethod(ASTNode)
    def visit(self, node):
        pass
