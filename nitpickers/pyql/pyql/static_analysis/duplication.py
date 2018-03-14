from multimethods import multimethod
from pyql.ast.form.form import Form
from pyql.ast.form.block import Block
from pyql.ast.form.ql_statements import Question
from pyql.ast.form.ql_statements import If
from pyql.ast.form.ql_statements import IfElse
from pyql.ast.ast import ASTNode
from pyql.util import message
from pyql.static_analysis.symbol_table import SymbolTable


class CheckDuplicatedQuestions:

    def __init__(self):
        self._messages = []
        self._symbol_table = SymbolTable()

    def check(self, tree):
        tree.accept(self)
        return self._messages

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
        [q.accept(self) for q in block.statements]

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
