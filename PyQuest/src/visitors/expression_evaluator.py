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
from AST.expressions.unary_operators.negative import NegativeOperatorNode
from AST.expressions.literals.integer_node import IntegerNode
from AST.expressions.literals.boolean_node import BooleanNode
from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode


class ExpressionEvaluator:
    def __init__(self, form):
        self.form = form
        self.result = None

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    # @when(BinaryOperatorNode)
    # def visit(self, node):
    #     node.left_expression.accept(self)
    #     node.right_expression.accept(self)
    #     node.value = node.operator.evaluate(node.left_expression, node.right_expression)
    #     self.result = node.value

    @when(VariableNode)
    def visit(self, node):
        for question in self.form.block:
            if node.identifier == question.identifier:
                node.value = question.answer.value

    @when(NegativeOperatorNode)
    def visit(self, node):
        node.expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(EqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(GreaterEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(GreaterThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(LessEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(LessThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(MultiplicationOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(NotEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(OrOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(IntegerNode)
    def visit(self, node):
        self.result = node.value

    @when(BooleanNode)
    def visit(self, node):
        self.result = node.value
