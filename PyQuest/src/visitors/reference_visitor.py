from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode
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
from AST.expressions.literals.decimal_node import DecimalNode
from src.visitors.visitor_helper import on, when


class ReferenceVisitor(object):

    def __init__(self):
        self.__current_block = []
        self.__current_scope = {}
        self.__scope_id = 0

    @property
    def scope(self):
        return self.__current_scope

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        self.__current_scope = {"id": self.__scope_id,
                                "content": [],
                                "children": []}

        self.__current_block = []

        for child in node.block:
            child.accept(self)

        self.__current_scope["content"] = self.__current_block

    @when(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        self.__current_scope["content"] += self.__current_block
        previous_scope = self.__current_scope
        previous_block = self.__current_block

        self.__current_block = []
        self.__current_scope = {"id": self.__scope_id,
                                "content": [],
                                "children": []}

        for child in node.block:
            child.accept(self)

        self.__current_scope["content"] = self.__current_block

        previous_scope["children"].append(self.__current_scope)

        self.__current_block = previous_block
        self.__current_scope = previous_scope

    @when(QuestionNode)
    def visit(self, node):
        self.__current_block.append(dict({"name": node.identifier, "type": node.answer_type}))

        if node.computed:
            node.answer.accept(self)

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

    @when(IntegerNode)
    def visit(self, node):
        pass

    @when(DecimalNode)
    def visit(self, node):
        pass

    @when(VariableNode)
    def visit(self, node):
        name = node.identifier
        self.__current_block.append({"name": name, "type": []})
