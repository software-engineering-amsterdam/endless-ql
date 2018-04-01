from multimethods import multimethod

from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.ast.nodes.expressions.literals.literal import LiteralNode
from ql.ast.nodes.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.nodes.expressions.variable import VariableNode
from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode


class ReferenceVisitor:
    def __init__(self):
        self.__current_block = []
        self.__current_scope = {}

    @property
    def identifier_scopes(self):
        return self.__current_scope

    @multimethod(FormNode)
    def visit(self, node):
        self.__current_scope = {
            'content':  [],
            'children': [],
        }
        self.__current_block = []

        for child in node.block:
            child.accept(self)

        self.__current_scope['content'] = self.__current_block

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)

        self.__current_scope['content'] += self.__current_block
        previous_scope = self.__current_scope
        previous_block = self.__current_block
        self.__current_block = []
        self.__current_scope = {
            'content':  [],
            'children': [],
        }

        for child in node.block:
            child.accept(self)

        self.__current_scope['content'] = self.__current_block
        previous_scope['children'].append(self.__current_scope)
        self.__current_block = previous_block
        self.__current_scope = previous_scope

    @multimethod(QuestionNode)
    def visit(self, node):
        self.__current_block.append(dict({
            'name': node.identifier,
            'type': node.answer_type,
            'line': node.metadata.line,
        }))

        if node.computed:
            node.answer.accept(self)

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @multimethod(VariableNode)
    def visit(self, node):
        name = node.identifier
        self.__current_block.append({
            'name': name,
            'type': [],
            'line': node.metadata.line,
        })

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
