from ql.ast.base_node import BaseNode
from ql.ast.statements.block_statement_node import BlockStatementNode
from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.expressions.expression_node import ExpressionNode
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
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
from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.expressions.literals.integer_node import IntegerNode
from ql.ast.expressions.literals.decimal_node import DecimalNode
import ql.ast.visitors.visitor_helper as visitor


class ASTVisitor(object):

    # Generic method that initializes the dynamic dispatcher
    @visitor.on('node')
    def visit(self, node):
        print('abc')
        pass

    # BaseNode will not be initialized directly
    @visitor.when(BaseNode)
    def visit(self, node):
        print("Unrecognized node:", node)

    # BlockStatementNode will not be initialized directly
    @visitor.when(BlockStatementNode)
    def visit(self, node):
        print("Unrecognized node:", node)

    @visitor.when(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

        print("Found form node: " + node.identifier)

    @visitor.when(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        for child in node.block:
            child.accept(self)

    @visitor.when(QuestionNode)
    def visit(self, node):
        if node.answer:
            node.answer.accept(self)

        print("Found node: " + node.label)

    # ExpressionNode will not be initialized directly
    @visitor.when(ExpressionNode)
    def visit(self, node):
        print("Unrecognized node:", node)

    # BinaryOperatorNode will not be initialized directly
    @visitor.when(BinaryOperatorNode)
    def visit(self, node):
        print("Unrecognized node:", node)

    # UnaryOperatorNode will not be initialized directly
    @visitor.when(UnaryOperatorNode)
    def visit(self, node):
        print("Unrecognized node:", node)

    @visitor.when(VariableNode)
    def visit(self, node):
        print("Variable node.")


    @visitor.when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(EqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(GreaterEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(GreaterThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(LessEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(LessThanOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(MultiplicationOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(NotEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(OrOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(IntegerNode)
    def visit(self, node):
        print(node.value)

    @visitor.when(DecimalNode)
    def visit(self, node):
        print(node.value)
