from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.visitors.visitor_helper import on, when


class IdentifierVisitor(object):

    def __init__(self):
        self.__identifiers = []

    @property
    def identifiers(self):
        return self.__identifiers

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @when(IfNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @when(QuestionNode)
    def visit(self, node):
        self.__identifiers.append(node.identifier)

