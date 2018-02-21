from nodes import BaseNode
from nodes import FormNode
from nodes import QuestionNode
import visitor_helper as visitor


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

    @visitor.when(FormNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        for child in node.children:
            child.accept(self)

        print("Found form node: " + node.get_label())

    @visitor.when(QuestionNode)
    def visit(self, node):
        """
        Will run for nodes that do specifically match the
        provided type.
        """
        print("Found node: " + node.get_question())