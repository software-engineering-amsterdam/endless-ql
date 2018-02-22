from AST.base_node import BaseNode
from AST.statements.block_statement_node import BlockStatementNode
from AST.statements.form_node import FormNode
from AST.statements.if_node import IfNode
from AST.statements.question_node import QuestionNode


import src.visitors.visitor_helper as visitor


class ASTVisitor(object):
    @visitor.on('node')
    def visit(self, node):
        """
        This is the generic method that initializes the
        dynamic dispatcher.
        """
        pass

    @visitor.when(BaseNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print("Unrecognized node:", node)

    @visitor.when(BlockStatementNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print("Unrecognized node:", node)

    @visitor.when(FormNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        for child in node.block:
            child.accept(self)

        print("Found form node: " + node.get_label())

    @visitor.when(IfNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        for child in node.block:
            child.accept(self)

        print("Found form node: " + node.get_label())

    @visitor.when(QuestionNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print("Found node: " + node.get_question())
        if node.expression:
            node.expression.accept(self)

    @visitor.when(ExpressionNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        pass

    @visitor.when(BinaryOperatorNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        node.left_expression.accept(self)
        node.right_expression.accept(self)

    @visitor.when(UnaryOperatorNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        node.expression.accept(self)

    @visitor.when(LiteralNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print(node.value)

    @visitor.when(VariableNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print(node.name)