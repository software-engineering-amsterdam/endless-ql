from multimethods import multimethod
from pyql.ast.form.form import *
from pyql.ast.form.block import *
from pyql.ast.form.ql_statements import *
from pyql.util import message
from pyql.static_analysis.symbol_table import SymbolTable
from pyql.ast.expression.expressions import *


class VariableDependenciesChecker:

    def __init__(self, tree):
        self._messages = []
        self._symbol_table = SymbolTable()
        self._tree = tree
        self._expressions_checker = ExpressionDependenciesChecker(self._symbol_table)

    def check(self):
        return self._tree.accept(self)

    @property
    def messages(self):
        return self._messages

    @property
    def symbol_table(self):
        return self._symbol_table

    @multimethod(Form)
    def visit(self, form):
        return form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        statements = block.statements
        res = []
        for q in statements:
            res += q.accept(self)
        for q in statements:
            if hasattr(q, "identifier"):
                try:
                    self._symbol_table.remove(q.identifier.identifier)
                except KeyError:
                    print(q.identifier.identifier, "already removed")
        return res

    @multimethod(ComputedQuestion)
    def visit(self, question):
        # print("check computed question")
        # print("{0} against {1}".format(question.expression, self._symbol_table))
        return self._expressions_checker.check(question.expression)

    @multimethod(Question)
    def visit(self, question):
        self._symbol_table.create(question.identifier.identifier, question)
        return []

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        # print("check if else")
        # print("{0} against {1}".format(if_else_statement.expression, self._symbol_table))
        messages = self._expressions_checker.check(if_else_statement.expression)
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)
        return messages

    @multimethod(If)
    def visit(self, if_statement):
        # print("check if")
        # print("{0} against {1}".format(if_statement.expression, self._symbol_table))
        messages = self._expressions_checker.check(if_statement.expression)
        if_statement.block.accept(self)
        return messages

    @multimethod(ASTNode)
    def visit(self, node):
        print("ASTNode: {0}".format(node))
        pass

    def _label_exists(self, question):
        for q in self._symbol_table.dictionary.items():
            if str(q[1].text) == str(question.text):
                return True
        return False


class ExpressionDependenciesChecker:

    def __init__(self, symbol_table=None):
        self._messages = []
        self._symbol_table = symbol_table

    def check(self, expression):
        return expression.accept(self)

    @multimethod(Identifier)
    def visit(self, identifier):
        try:
            self._symbol_table.get(identifier.identifier)
            return []
        except KeyError:
            return [message.Error("Identifier {0} undefined".format(identifier.identifier))]

    @multimethod(BinaryExpression)
    def visit(self, expression):
        return expression.left.accept(self) + expression.right.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        return unary_expression.expression.accept(self)

    @multimethod(Expression)
    def visit(self, expression):
        return []
