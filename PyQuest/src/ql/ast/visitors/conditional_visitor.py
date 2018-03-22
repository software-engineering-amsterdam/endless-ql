from debug.errors.conditional_error import ConditionalError
from multimethods import multimethod
from ql.ast.statements.form_node import FormNode
from ql.ast.statements.if_node import IfNode
from ql.ast.statements.question_node import QuestionNode
from ql.types.boolean import QLBoolean


class ConditionalVisitor:
    def __int__(self):
        pass

    @multimethod(FormNode)
    def visit(self, node):
        for child in node.block:
            child.accept(self)

    @multimethod(IfNode)
    def visit(self, node):
        if node.condition.expression_type != QLBoolean:
            raise ConditionalError('Condition does not evaluate to boolean, at line {}.'.format(node.position.line))

        for child in node.block:
            child.accept(self)

    @multimethod(QuestionNode)
    def visit(self, node):
        pass
