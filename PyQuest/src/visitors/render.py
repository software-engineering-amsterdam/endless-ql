from AST.base_node import BaseNode
from AST.statements.block_statement_node import BlockStatementNode
from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode
from AST.expressions.expression_node import ExpressionNode
from AST.expressions.variable_node import VariableNode
from AST.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
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
from AST.expressions.unary_operators.unary_operator import UnaryOperatorNode
from AST.expressions.literals.integer_node import IntegerNode
from AST.expressions.literals.decimal_node import DecimalNode
import src.visitors.visitor_helper as visitor
from uuid import uuid4
from PyQt5.QtWidgets import (QApplication, QComboBox, QDialog,
                             QDialogButtonBox, QFormLayout, QGridLayout, QGroupBox, QHBoxLayout,
                             QLabel, QLineEdit, QMenu, QMenuBar, QPushButton, QSpinBox, QTextEdit,
                             QVBoxLayout)

class Render(object):

    def __init__(self, dialog):
        self.dialog = dialog

    # Generic method that initializes the dynamic dispatcher
    @visitor.on('node')
    def visit(self, node):
        pass

    # BaseNode will not be initialized directly
    @visitor.when(BaseNode)
    def visit(self, node):
        pass

    # BlockStatementNode will not be initialized directly
    @visitor.when(BlockStatementNode)
    def visit(self, node):
        pass

    @visitor.when(FormNode)
    def visit(self, node):
        self.dialog.formGroupBox = QGroupBox("Form layout")
        self.dialog.layout = QFormLayout()

        for child in node.block:
            child.accept(self)

        self.dialog.formGroupBox.setLayout(self.dialog.layout)

        buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)
        buttonBox.accepted.connect(self.dialog.accept)
        buttonBox.rejected.connect(self.dialog.reject)

        mainLayout = QVBoxLayout()
        mainLayout.addWidget(self.dialog.formGroupBox)
        mainLayout.addWidget(buttonBox)
        self.dialog.setLayout(mainLayout)

        self.dialog.setWindowTitle("Form Layout")

    @visitor.when(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        for child in node.block:
            child.accept(self)

    @visitor.when(QuestionNode)
    def visit(self, node):
        node.pyqt5_render(self.dialog.layout)
        if node.expression:
            node.expression.accept(self)

    # ExpressionNode will not be initialized directly
    @visitor.when(ExpressionNode)
    def visit(self, node):
        pass

    # BinaryOperatorNode will not be initialized directly
    @visitor.when(BinaryOperatorNode)
    def visit(self, node):
        pass

    # UnaryOperatorNode will not be initialized directly
    @visitor.when(UnaryOperatorNode)
    def visit(self, node):
        pass

    @visitor.when(VariableNode)
    def visit(self, node):
        pass

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
        pass

    @visitor.when(DecimalNode)
    def visit(self, node):
        pass