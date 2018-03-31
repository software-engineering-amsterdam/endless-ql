from multimethods import multimethod
from pyql.ast.expression.expressions import *
from util.message_handler import MessageHandler
from util import message
from pyql.ast.form.form import *
from pyql.ast.form.ql_statements import *
from pyql.ast.form.block import *
import decimal
from pyql.util.values import *


class CheckDivisionByZero:

    def check(self, tree):
        tree.accept(self)

    @multimethod(Division)
    def visit(self, expression):
        v = expression.right.value
        i = IntegerValue(0)
        b = expression.right.value == IntegerValue(0)
        if isinstance(expression.right, Literal) and expression.right.value == IntegerValue(0):
            MessageHandler().add(message.Error("Division by zero: " + str(expression)))
        expression.left.accept(self)
        expression.right.accept(self)

    @multimethod(BinaryExpression)
    def visit(self, expression):
        expression.left.accept(self)
        expression.right.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        unary_expression.expression.accept(self)

    @multimethod(Expression)
    def visit(self, expression):
        return expression

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
        computed_question.expression.accept(self)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.expression.accept(self)
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.expression.accept(self)
        if_statement.block.accept(self)

    @multimethod(ASTNode)
    def visit(self, node):
        pass
