from multimethods import multimethod

from ql.ast.nodes.expressions.binary_operators.binary_operator import BinaryOperatorNode
from ql.ast.nodes.expressions.literals.literal import LiteralNode
from ql.ast.nodes.expressions.unary_operators.unary_operator import UnaryOperatorNode
from ql.ast.nodes.expressions.variable import VariableNode
from ql.ast.nodes.statements.form_statement import FormNode
from ql.ast.nodes.statements.if_statement import IfNode
from ql.ast.nodes.statements.question_statement import QuestionNode


class DependencyVisitor:
    def __init__(self):
        self.__all_dependencies = []
        self.__current_dependencies = []

    @property
    def identifier_dependencies(self):
        return self.__all_dependencies

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        node.condition.accept(self)
        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        if node.computed:
            self.__current_dependencies = []
            node.answer.accept(self)
            self.__all_dependencies.append((node.identifier, self.__current_dependencies, node.metadata))

    @multimethod(BinaryOperatorNode)
    def visit(self, node):
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @multimethod(UnaryOperatorNode)
    def visit(self, node):
        node.expression.accept(self)

    @multimethod(VariableNode)
    def visit(self, node):
        self.__current_dependencies.append(node.identifier)

    @multimethod(LiteralNode)
    def visit(self, node):
        pass
