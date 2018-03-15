from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
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
from ql.ast.expressions.literals.integer_node import IntegerNode
from ql.ast.expressions.literals.decimal_node import DecimalNode
from ql.ast.expressions.literals.undefined_node import UndefinedNode
from ql.ast.visitors.visitor_helper import when, on
from ql.types.undefined import QLUndefined
from debug.debug import error


class TypeChecker:

    def __init__(self, identifier_type_combinations):
        self.__identifier_type_combinations = identifier_type_combinations

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
            node.answer.accept(self)

            result_type = node.answer.expression_type

            if node.answer_type != result_type:
                error(node.answer.position.line, "Expression not of type {}".format(node.answer_type))

    @when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

        node.set_expression_type(result_type)

    @when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

    @when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

        node.set_expression_type(result_type)

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

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

        node.set_expression_type(result_type)

    @when(NotEqualsOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @when(OrOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

    @when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            error(node.position.line, "Invalid operand(s)")

        node.set_expression_type(result_type)

    @when(DecimalNode)
    def visit(self, node):
        pass

    @when(IntegerNode)
    def visit(self, node):
        pass

    @when(VariableNode)
    def visit(self, node):
        for combination in self.__identifier_type_combinations:
            if combination["identifier"] == node.identifier:
                node.set_expression_type(combination["answer_type"])

    @when(UndefinedNode)
    def visit(self, node):
        pass

