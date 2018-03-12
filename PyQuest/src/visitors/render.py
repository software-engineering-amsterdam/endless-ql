from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode
from AST.expressions.binary_operators.and_node import AndOperatorNode
from AST.types.type_boolean import TypeBoolean
from src.visitors.visitor_helper import when, on
from render.form import Form


class Render(object):
    def __init__(self):
        self.form = None
        self.condition = TypeBoolean.get_literal_node(True)

    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        self.form = Form(node.identifier)

        for child in node.block:
            child.accept(self)

    @when(IfNode)
    def visit(self, node):
        previous_condition = self.condition
        self.condition = AndOperatorNode(None, TypeBoolean, self.condition, node.condition, None)

        for child in node.block:
            child.accept(self)

        self.condition = previous_condition

    @when(QuestionNode)
    def visit(self, node):
        self.form.block.append(node.to_question(self.condition))
