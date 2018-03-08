from src.visitors.visitor_helper import when, on
from AST.expressions.variable_node import VariableNode
from AST.expressions.binary_operators.addition_node import AdditionOperatorNode
from AST.expressions.binary_operators.and_node import AndOperatorNode
from AST.expressions.binary_operators.division_node import DivisionOperatorNode
from AST.expressions.binary_operators.equals_node import EqualsOperatorNode
from AST.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from AST.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from AST.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from AST.expressions.binary_operators.less_than_node import LessThanOperatorNode
from AST.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from AST.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from AST.expressions.binary_operators.or_node import OrOperatorNode
from AST.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from AST.expressions.literals.integer_node import IntegerNode


class ExpressionEvaluator:
    def __init__(self, form):
        self.form = form
        self.result = None

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    @when(VariableNode)
    def visit(self, node):
        # TODO symbol table value lookup
        for question in self.form.block:
            if node.identifier == question.identifier:
                node.value = question.answer

    @when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value + node.right_expression.value

        self.result = node.value

    @when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value and node.right_expression.value

        self.result = node.value

    @when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value / node.right_expression.value

        self.result = node.value

    @when(EqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value == node.right_expression.value

        self.result = node.value

    @when(GreaterEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value >= node.right_expression.value

        self.result = node.value

    @when(GreaterThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value > node.right_expression.value

        self.result = node.value

    @when(LessEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value <= node.right_expression.value

        self.result = node.value

    @when(LessThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value < node.right_expression.value

        self.result = node.value

    @when(MultiplicationOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value * node.right_expression.value

        self.result = node.value

    @when(NotEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value != node.right_expression.value

        self.result = node.value

    @when(OrOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value or node.right_expression.value

        self.result = node.value

    @when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        if node.left_expression.value and node.right_expression.value:
            node.value = node.left_expression.value - node.right_expression.value

        self.result = node.value

    @when(IntegerNode)
    def visit(self, node):
        pass
