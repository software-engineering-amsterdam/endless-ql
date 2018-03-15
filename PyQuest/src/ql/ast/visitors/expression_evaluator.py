from ql.ast.visitors.visitor_helper import when, on
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.addition_node import AdditionOperatorNode
from ql.ast.expressions.binary_operators.and_node import AndOperatorNode
from ql.ast.expressions.binary_operators.division_node import DivisionOperatorNode
from ql.ast.expressions.binary_operators.equals_node import EqualsOperatorNode
from ql.ast.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from ql.ast.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from ql.ast.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from ql.ast.expressions.binary_operators.less_than_node import LessThanOperatorNode
from ql.ast.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from ql.ast.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from ql.ast.expressions.binary_operators.or_node import OrOperatorNode
from ql.ast.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from ql.ast.expressions.unary_operators.negation import NegationOperatorNode
from ql.ast.expressions.unary_operators.negative import NegativeOperatorNode
from ql.ast.expressions.literals.boolean_node import BooleanNode
from ql.ast.expressions.literals.decimal_node import DecimalNode
from ql.ast.expressions.literals.integer_node import IntegerNode
from ql.ast.expressions.literals.money_node import MoneyNode


class ExpressionEvaluator:
    def __init__(self, form):
        self.form = form
        self.result = None

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    def unary_operator_visit(self, node):
        node.expression.accept(self)
        node.evaluate()
        self.result = node.value
    
    def binary_operator_visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)
        node.evaluate()
        self.result = node.value

    @when(VariableNode)
    def visit(self, node):
        for question in self.form.block:
            if node.identifier == question.identifier:
                node.value = question.answer.value

    @when(NegationOperatorNode)
    def visit(self, node):
        self.unary_operator_visit(node)

    @when(NegativeOperatorNode)
    def visit(self, node):
        self.unary_operator_visit(node)

    @when(AdditionOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(AndOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(DivisionOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(EqualsOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(GreaterEqualsOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(GreaterThanOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(LessEqualsOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(LessThanOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(MultiplicationOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(NotEqualsOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(OrOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(SubtractionOperatorNode)
    def visit(self, node):
        self.binary_operator_visit(node)

    @when(BooleanNode)
    def visit(self, node):
        self.result = node.value

    @when(DecimalNode)
    def visit(self, node):
        self.result = node.value

    @when(IntegerNode)
    def visit(self, node):
        self.result = node.value

    @when(MoneyNode)
    def visit(self, node):
        self.result = node.value
