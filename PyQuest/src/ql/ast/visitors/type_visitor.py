from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.ast.expressions.variable_node import VariableNode
from ql.ast.expressions.binary_operators.binary_operator_node import BinaryOperatorNode
from ql.ast.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.expressions.literals.literal_node import LiteralNode
from ql.types.undefined import QLUndefined
from multimethods import multimethod


class TypeVisitor:

    def __init__(self, symbol_table, debug):
        self.__symbol_table = symbol_table
        self.__debug = debug
        self.__errors = False

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

        return self.__errors

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)
        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        if node.computed:
            node.answer.accept(self)

            result_type = node.answer.expression_type

            if node.answer_type != result_type:
                self.__debug.error([node.answer.position.line], 'Expression not of type {}'.format(node.answer_type))
                self.__errors = True

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type(node.left_expression.expression_type, node.right_expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

        result_type = node.get_result_type(node.expression.expression_type)

        if result_type == QLUndefined:
            self.__debug.error([node.position.line], 'Invalid operand(s)')
            self.__errors = True

        node.set_expression_type(result_type)

    @multimethod(VariableNode)
    def visit(self, node):
        for row in self.__symbol_table:
            if row['identifier'] == node.identifier:
                node.set_expression_type(row['answer_type'])

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
