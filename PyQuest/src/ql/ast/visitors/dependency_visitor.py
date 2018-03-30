from ql.ast.nodes.statements.form_node import FormNode
from ql.ast.nodes.statements.if_node import IfNode
from ql.ast.nodes.statements.question_node import QuestionNode
from ql.ast.nodes.expressions.variable_node import VariableNode
from ql.ast.nodes.expressions.binary_operators.addition_node import AdditionOperatorNode
from ql.ast.nodes.expressions.binary_operators.and_node import AndOperatorNode
from ql.ast.nodes.expressions.binary_operators.division_node import DivisionOperatorNode
from ql.ast.nodes.expressions.binary_operators.equals_node import EqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.greater_equals_node import GreaterEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.greater_than_node import GreaterThanOperatorNode
from ql.ast.nodes.expressions.binary_operators.less_equals_node import LessEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.less_than_node import LessThanOperatorNode
from ql.ast.nodes.expressions.binary_operators.multiplication_node import MultiplicationOperatorNode
from ql.ast.nodes.expressions.binary_operators.not_equals_node import NotEqualsOperatorNode
from ql.ast.nodes.expressions.binary_operators.or_node import OrOperatorNode
from ql.ast.nodes.expressions.binary_operators.subtraction_node import SubtractionOperatorNode
from ql.ast.nodes.expressions.unary_operators.negation_node import NegationOperatorNode
from ql.ast.nodes.expressions.unary_operators.negative_node import NegativeOperatorNode
from ql.ast.nodes.expressions.literals.boolean_node import BooleanNode
from ql.ast.nodes.expressions.literals.decimal_node import DecimalNode
from ql.ast.nodes.expressions.literals.integer_node import IntegerNode
from ql.ast.nodes.expressions.literals.string_node import StringNode
from ql.ast.nodes.expressions.literals.date_node import DateNode
from ql.ast.nodes.expressions.literals.money_node import MoneyNode
from ql.ast.visitors.visitor_helper import on, when


class DependencyVisitor(object):

    def __init__(self):
        self.__all_dependencies = []
        self.__current_dependencies = []

    @property
    def identifier_dependencies(self):
        return self.__all_dependencies

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @when(IfNode)
    def visit(self, node):
        node.condition.accept(self)
        for child in node.block:
            child.accept(self)

    @when(QuestionNode)
    def visit(self, node):
        if node.computed:
            self.__current_dependencies = []
            node.answer.accept(self)
            self.__all_dependencies.append((node.identifier,
                                            self.__current_dependencies,
                                            node.position))

    @when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(EqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(GreaterEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(GreaterThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(LessEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(LessThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(MultiplicationOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(NotEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(OrOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(NegationOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @when(NegativeOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @when(BooleanNode)
    def visit(self, node):
        pass

    @when(DateNode)
    def visit(self, node):
        pass

    @when(DecimalNode)
    def visit(self, node):
        pass

    @when(IntegerNode)
    def visit(self, node):
        pass

    @when(MoneyNode)
    def visit(self, node):
        pass

    @when(StringNode)
    def visit(self, node):
        pass

    @when(VariableNode)
    def visit(self, node):
        self.__current_dependencies.append(node.identifier)
