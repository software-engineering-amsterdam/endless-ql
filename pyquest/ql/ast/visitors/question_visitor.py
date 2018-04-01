from multimethods import multimethod

from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode


class QuestionVisitor:
    def __init__(self):
        self.__questions = []

    @property
    def questions(self):
        return self.__questions

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
        self.__questions.append({
            'label': node.label,
            'identifier': node.identifier,
            'answer_type': node.answer_type,
            'metadata': node.metadata,
        })
