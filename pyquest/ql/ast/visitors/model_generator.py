from multimethods import multimethod

from gui.model.form import FormModel
from gui.model.question import QuestionModel
from ql.ast.nodes.expressions.binary_operators.and_operator import AndOperatorNode
from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class ModelGenerator:
    def __init__(self):
        self.form = None
        self.condition = QLBoolean.get_literal_node(True)

    @multimethod(FormNode)
    def visit(self, node):
        self.form = FormModel(node.identifier)

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
        question_model = QuestionModel(node.label, node.identifier, node.answer_type,
                                       node.answer, node.computed, self.condition)
        self.form.block.append(question_model)
