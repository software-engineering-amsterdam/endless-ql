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
from ql.ast.expressions.unary_operators.negation import NegationOperatorNode
from ql.ast.expressions.unary_operators.negative import NegativeOperatorNode
from ql.ast.expressions.literals.integer_node import IntegerNode
from ql.ast.expressions.literals.decimal_node import DecimalNode
from ql.ast.expressions.literals.date_node import DateNode
from ql.ast.expressions.literals.undefined_node import UndefinedNode
from ql.ast.visitors.visitor_helper import when, on
from ql.types.undefined import QLUndefined


class TypeVisitor:

    def __init__(self, symbol_table, debug):
        self.__symbol_table = symbol_table
        self.__debug = debug
        self.__errors = False

    # Generic method that initializes the dynamic dispatcher
    @on('node')
    def visit(self, node):
        pass

    @when(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

        return self.__errors

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
                self.__debug.error([node.answer.position.line], 'Expression not of type {}'.format(node.answer_type))
                self.__errors = True

    @when(AdditionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @when(AndOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

    @when(DivisionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

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
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

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
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

    @when(SubtractionOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @when(NegationOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

        result_type = node.get_result_type(node.expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @when(NegativeOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

        result_type = node.get_result_type(node.expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @when(DecimalNode)
    def visit(self, node):
        pass

    @when(IntegerNode)
    def visit(self, node):
        pass

    @when(DateNode)
    def visit(self, node):
        pass

    @when(VariableNode)
    def visit(self, node):
        for row in self.__symbol_table:
            if row['identifier'] == node.identifier:
                node.set_expression_type(row['answer_type'])

    @when(UndefinedNode)
    def visit(self, node):
        pass

