from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.expressions.binary_operators.and_node import AndOperatorNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined
from gui.model.form import Form
from multimethods import multimethod


class GUIModel:
    def __init__(self):
        self.form = None
        self.condition = QLBoolean.get_literal_node(True)

    @multimethod(FormNode)
    def visit(self, node):
        self.form = Form(node.identifier)

        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        previous_condition = self.condition
        self.condition = AndOperatorNode(None, QLBoolean, self.condition, node.condition, QLUndefined())

        for child in node.block:
            child.accept(self)

        self.condition = previous_condition

    @multimethod(QuestionNode)
    def visit(self, node):
        self.form.block.append(node.to_question(self.condition))
