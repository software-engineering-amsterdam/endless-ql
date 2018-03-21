from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.expressions.literals.literal_node import LiteralNode
from ql.ast.expressions.variable_node import VariableNode
from multimethods import multimethod


class ExpressionEvaluator:
    def __init__(self, form):
        self.form = form
        self.result = None

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
