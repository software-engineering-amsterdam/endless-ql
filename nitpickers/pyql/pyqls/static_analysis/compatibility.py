from multimethods import multimethod
from util import types
from pyqls.ast.nodes.statement import QuestionStyle
from pyqls.ast.nodes.stylesheet import StyleSheet
from pyqls.ast.nodes.block import Block
from pyqls.ast.nodes.page import Page
from pyqls.ast.nodes.section import Section
from pyqls.ast.nodes import widget
from util.ast import ASTNode


class CompatibilityTypesWidget:

    def __init__(self, questions):
        self._questions = questions

    def check(self, tree):
        print("Checking compatibility between types and widgets")
        tree.accept(self)

    @multimethod(widget.Widget)
    def visit(self, widget):
        print("Checking widget", widget)
        self.compatible(widget, widget.type)

    @multimethod(QuestionStyle)
    def visit(self, question_style):
        widget_type = question_style.widget.type
        question = self._questions[question_style.identifier]

        print("Checking question {0} of type {1} against widget {2}".format(question.identifier, question.question_type, widget_type))
        print("Are they compatible?", self.compatible(widget_type, question.question_type))
        # question_style.widget.accept(self)

    @multimethod(Block)
    def visit(self, block):
        for statement in block.statements:
            statement.accept(self)

    @multimethod(StyleSheet)
    def visit(self, stylesheet):
        stylesheet.block.accept(self)

    @multimethod(Page)
    def visit(self, page):
        page.block.accept(self)

    @multimethod(Section)
    def visit(self, section):
        section.block.accept(self)

    @multimethod(ASTNode)
    def visit(self, _):
        pass

    @multimethod([(widget.CheckBox, types.Boolean),
                  (widget.DropDown, types.Boolean),
                  (widget.Text, types.String),
                  (widget.SpinBox, types.Integer),
                  (widget.Text, types.Integer),
                  (widget.DropDown, types.Integer),
                  (widget.SpinBox, types.Money),
                  (widget.Text, types.Decimal),
                  (widget.SpinBox, types.Decimal)])
    def compatible(self, one, another):
        return True

    @multimethod(widget.WidgetType, types.Type)
    def compatible(self, one, another):
        return False
