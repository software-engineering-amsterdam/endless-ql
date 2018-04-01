from multimethods import multimethod

from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.ast.nodes.expressions.literals.literal import LiteralNode
from ql.ast.nodes.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.nodes.expressions.variable import VariableNode
from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode
from ql.types.boolean import QLBoolean
from ql.types.undefined import QLUndefined


class TypeVisitor:
    def __init__(self, symbol_table):
        self.__symbol_table = symbol_table
        self.__errors = []

    @property
    def errors(self):
        return self.__errors

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        if node.condition.expression_type != QLBoolean:
            self.__errors.append('Condition does not evaluate to boolean on line {}.'
                                 .format(node.metadata.line))

        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        if node.computed:
            node.answer.accept(self)

            result_type = node.answer.expression_type

            if node.answer_type != result_type:
                self.__errors.append('Expression not of type {} on line {}'
                                     .format(node.answer_type, node.answer.metadata.line))

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

        result_type = node.get_result_type()

        if result_type == QLUndefined:
            self.__errors.append('Invalid operands: {} and {} not supported for binary operation on line {}'
                                 .format(node.left_expression.expression_type,
                                         node.right_expression.expression_type,
                                         node.metadata.line))

        node.expression_type = result_type

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

        result_type = node.get_result_type()

        if result_type == QLUndefined:
            self.__errors.append('Invalid operand: {} not supported for unary operation on line {}'
                                 .format(node.expression.expression_type, node.metadata.line))

        node.expression_type = result_type

    @multimethod(VariableNode)
    def visit(self, node):
        for row in self.__symbol_table:
            if row['identifier'] == node.identifier:
                node.expression_type = row['answer_type']

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
