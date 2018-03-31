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
from pyql.static_analysis.expression_evaluator import ExpressionEvaluator


class GUIVisitor:
    def __init__(self, gui_window, symbol_table):
        self._gui_window = gui_window
        self._symbol_table = symbol_table
        self._expression_visitor = ExpressionEvaluator(self._symbol_table)

    @multimethod(Identifier)
    def visit(self, identifier):
        return identifier.identifier

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        questions = block.statements
        for q in questions:
            visit_accepted = q.accept(self)
            if visit_accepted is not None:
                break

    @multimethod(ComputedQuestion)
    def visit(self, computed_question):
        try:
            expression_result = computed_question.expression.accept(self._expression_visitor).value
        except KeyError:
            return False

        if expression_result:
            identifier = computed_question.identifier.accept(self)
            type = computed_question.question_type
            text = computed_question.text

            self._gui_window.add_computed_question(identifier, type, text, expression_result)

    @multimethod(Question)
    def visit(self, question):
        identifier = question.identifier.accept(self)
        type = question.question_type
        text = question.text

        try:
            saved_value = self._symbol_table.get(identifier).value
        except KeyError:
            saved_value = ""

        self._gui_window.add_question(identifier, type, text, saved_value)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        try:
            expression_evaluates_true = if_else_statement.expression.accept(self._expression_visitor)
        except KeyError:
            return False

        if expression_evaluates_true:
            if_else_statement.if_block.accept(self)
        else:
            if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        try:
            expression_evaluates_true = if_statement.expression.accept(self._expression_visitor)
        except KeyError:
            return False

        if expression_evaluates_true:
            if_statement.block.accept(self)

    @multimethod(Type)
    def visit(self, type):
        return type

    @multimethod(ASTNode)
    def visit(self, node):
        pass
