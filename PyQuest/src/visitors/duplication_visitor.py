from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode
from src.visitors.visitor_helper import when, on


class DuplicationVisitor(object):

    def __init__(self):
        self.__question_nodes = []

    @property
    def question_nodes(self):
        return self.__question_nodes

    def add_question_node(self, node):
        self.__question_nodes.append(node)

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

        identifiers = [node.identifier for node in self.question_nodes]
        duplicate_label_nodes = [check_node for check_node in self.question_nodes if node.label == check_node.label]

        if node.identifier in identifiers:
            print("[Error]: Duplicate identifier found in question on line: ", node.position.line)

        for duplicate_node in duplicate_label_nodes:
            if isinstance(duplicate_node.answer_type, type(node.answer_type)):
                print("[Warning]: Duplicate label found in question on line: ", node.position.line)
            else:
                print("[Error]: Duplicate question found on line: ", node.position.line)

        self.add_question_node(node)
