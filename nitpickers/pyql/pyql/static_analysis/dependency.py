from multimethods import multimethod
from pyql.ast.form.form import *
from pyql.ast.form.block import *
from pyql.ast.form.ql_statements import *
from pyql.util import message
from pyql.util.message_handler import MessageHandler
from pyql.static_analysis.symbol_table import SymbolTable
from pyql.ast.expression.expressions import *
from collections import defaultdict


class VariableDependenciesChecker:

    def __init__(self):
        self._symbol_table = SymbolTable()
        self._expressions_checker = ExpressionDependenciesChecker(self._symbol_table)

    def check(self, tree):
        tree.accept(self)

    @property
    def symbol_table(self):
        return self._symbol_table

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        statements = block.statements
        for q in statements:
            q.accept(self)
        for q in statements:
            if hasattr(q, "identifier"):
                try:
                    self._symbol_table.remove(q.identifier.identifier)
                except KeyError:
                    print(q.identifier.identifier, "already removed")

    @multimethod(ComputedQuestion)
    def visit(self, question):
        self._symbol_table.create(question.identifier.identifier, question)
        return self._expressions_checker.check(question.expression)

    @multimethod(Question)
    def visit(self, question):
        self._symbol_table.create(question.identifier.identifier, question)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        self._expressions_checker.check(if_else_statement.expression)
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        self._expressions_checker.check(if_statement.expression)
        if_statement.block.accept(self)

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
        self._symbol_table = symbol_table

    def check(self, expression):
        expression.accept(self)

    @multimethod(Identifier)
    def visit(self, identifier):
        try:
            self._symbol_table.get(identifier.identifier)
        except KeyError:
            MessageHandler().add(message.Error("Identifier {0} undefined".format(identifier.identifier)))

    @multimethod(BinaryExpression)
    def visit(self, expression):
        expression.left.accept(self)
        expression.right.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        unary_expression.expression.accept(self)

    @multimethod(Expression)
    def visit(self, _):
        pass


class CyclicDependenciesChecker:

    def __init__(self):
        self.graph = defaultdict(list)
        self.visited = defaultdict(bool)
        self.stack = []

    def add_edge(self, a, b):
        self.graph[a].append(b)

    def print_graph(self):
        print(self.graph)

    def dfs(self, start):
        self.stack = [start]
        print("Starting traversal from", start)
        while len(self.stack) > 0:
            element = self.stack.pop()
            if not self.visited[str(element)]:
                print("visiting", element)
                self.visited[str(element)] = True
                for x in self.graph[element]:
                    self.stack.append(x)
            else:
                MessageHandler().add(message.Error("Identifier {0} is involved in a cyclic dependency".format(element)))

    def check_cycles(self):
        for x in self.graph:
            self.visited[x] = False
        for x in [str(x) for x in self.graph.keys()]:
            self.dfs(x)

    def check(self, tree):
        tree.accept(self)
        self.print_graph()
        self.check_cycles()

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        statements = block.statements
        for q in statements:
            q.accept(self)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.block.accept(self)

    @multimethod(ComputedQuestion)
    def visit(self, question):
        for x in question.expression.accept(self):
            self.add_edge(question.identifier.identifier, x)

    @multimethod(Identifier)
    def visit(self, identifier):
        return [identifier]

    @multimethod(BinaryExpression)
    def visit(self, expression):
        return expression.left.accept(self) + expression.right.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        return unary_expression.expression.accept(self)

    @multimethod(Expression)
    def visit(self, _):
        pass

    @multimethod(ASTNode)
    def visit(self, node):
        pass
