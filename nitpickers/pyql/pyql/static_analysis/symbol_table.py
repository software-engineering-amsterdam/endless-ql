from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from pyql.ast.ast import ASTNode
from pyql.util import message


class SymbolTable:

    def __init__(self):
        self._dictionary = {}

    @property
    def dictionary(self):
        return self._dictionary

    def create(self, key, value):
        if key in self._dictionary:
            raise KeyError("Key {0} already exists".format(key))
        print("Creating {0}".format(key))
        self._dictionary[key] = value

    def update(self, key, value):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        self._dictionary[key] = value

    def get(self, key):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        return self._dictionary[key]

    def remove(self, key):
        if key not in self._dictionary:
            raise KeyError("Invalid key: {0}".format(key))
        print("Removing {0}".format(key))
        del self._dictionary[key]

    def __str__(self):
        res = "Keys: "
        for key in self._dictionary.keys():
            res += key + " "
        return res


class SymbolTableBuilder:

    def __init__(self):
        self._messages = []
        self._symbol_table = SymbolTable()

    def build_from_tree(self, tree):
        tree.accept(self)
        return self._symbol_table

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
        for q in statements:
            q.accept(self)
        print("Doing something in the block")
        for q in statements:
            if hasattr(q, "identifier"):
                self._symbol_table.remove(q.identifier.identifier)

    @multimethod(Question)
    def visit(self, question):
        if self._label_exists(question):
            self._messages.append(message.Warning("Duplicate label: {0}".format(question.text)))
        try:
            self._symbol_table.create(question.identifier.identifier, question)
        except KeyError:
            self._messages.append(message.Error("Duplicate question: {0}".format(question.identifier.identifier)))

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        print("Evaluating {0}".format(if_else_statement.expression))
        print(self._symbol_table)
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        print("Evaluating {0}".format(if_statement.expression))
        print(self._symbol_table)
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
