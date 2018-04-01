from multimethods import multimethod

from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode


class IdentifierVisitor:
    def __init__(self):
        self.__identifiers = []

    @property
    def identifiers(self):
        return self.__identifiers

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        self.__identifiers.append(node.identifier)
