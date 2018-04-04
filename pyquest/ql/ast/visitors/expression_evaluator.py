from multimethods import multimethod

from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.ast.nodes.expressions.literals.literal import LiteralNode
from ql.ast.nodes.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.nodes.expressions.variable import VariableNode
from ql.types.undefined import QLUndefined


class ExpressionEvaluator:
    def __init__(self, form):
        self.form = form
        self.result = QLUndefined()

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)
        node.evaluate()
        self.result = node.value

    @multimethod(LiteralNode)
    def visit(self, node):
        self.result = node.value

    @multimethod(VariableNode)
    def visit(self, node):
        for question in self.form.block:
            if node.identifier == question.identifier:
                node.value = question.answer.value
                self.result = node.value
