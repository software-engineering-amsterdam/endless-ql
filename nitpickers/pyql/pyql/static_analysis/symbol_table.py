from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from pyql.ast.ast import ASTNode


class SymbolTable:

    def __init__(self):
        self._dictionary = {}

    @property
    def dictionary(self):
        return self._dictionary

    def create(self, key, value):
        if key in self._dictionary:
            raise Exception("Key {0} already exists".format(key))
        self._dictionary[key] = value

    def update(self, key, value):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        self._dictionary[key] = value

    def get(self, key):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        return self._dictionary[key]

    def remove(self, key):
        if key not in self._dictionary:
            raise Exception("Invalid key: {0}".format(key))
        del self._dictionary[key]


class SymbolTableBuilder:

    def __init__(self):
        self.symbol_table = SymbolTable()

    def build(self, tree):
        tree.accept(self)
        return self.symbol_table

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        for q in block.statements:
            q.accept(self)

    @multimethod(Question)
    def visit(self, question):
        self.symbol_table.create(question.identifier.identifier, question)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.block.accept(self)

    @multimethod(ASTNode)
    def visit(self, node):
        print("ASTNode: {0}".format(node))
        pass
