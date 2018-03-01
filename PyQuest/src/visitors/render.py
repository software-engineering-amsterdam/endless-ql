from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode
from AST.expressions.literals.boolean_node import BooleanNode
from AST.types.type_boolean import TypeBoolean
from AST.position import Position
from src.visitors.visitor_helper import when, on
from placeholder_name_object.form import Form


class Render(object):
    def __init__(self):
        self.form = None
        self.condition = None

    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        self.form = Form(node.identifier)
        self.condition = BooleanNode(Position(0, 0), TypeBoolean, True)

        for child in node.block:
            child.accept(self)

    @when(IfNode)
    def visit(self, node):
        previous_condition = self.condition
        self.condition = node.condition

        for child in node.block:
            child.accept(self)

        self.condition = previous_condition

    @when(QuestionNode)
    def visit(self, node):
        self.form.block.append(node.to_question(self.condition))
