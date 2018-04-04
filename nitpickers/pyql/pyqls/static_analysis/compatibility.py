from pyqls.ast.nodes import widget
from pyqls.ast.nodes.block import Block
from pyqls.ast.nodes.page import Page
from pyqls.ast.nodes.section import Section
from pyqls.ast.nodes.statement import Default
from pyqls.ast.nodes.statement import QuestionStyle
from pyqls.ast.nodes.stylesheet import StyleSheet
from util import types
from util.ast import ASTNode
from util.message import Error
from util.message_handler import MessageHandler
from util.multimethods import multimethod


class CompatibilityTypesWidget:

    def __init__(self, questions):
        self._questions = questions

    def check(self, tree):
        tree.accept(self)

    @multimethod(QuestionStyle)
    def visit(self, question_style):
        widget_type = question_style.widget.type
        question = self._questions[question_style.identifier]
        if not self._compatible(widget_type, question.question_type):
            MessageHandler().add(
                Error("{0} widget is not compatible with question type {1} for question {2}".format(widget_type,
                                                                                                    question.question_type,
                                                                                                    question.identifier)))

    @multimethod(Default)
    def visit(self, default):
        if not self._compatible(default.widget.type, default.question_type):
            MessageHandler().add(Error("{0} widget is not compatible with question type {1}".format(default.widget.type,
                                                                                                    default.question_type)))

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
                  (widget.Radio, types.Boolean),
                  (widget.Text, types.String),
                  (widget.SpinBox, types.Integer),
                  (widget.Text, types.Integer),
                  (widget.DropDown, types.Integer),
                  (widget.SpinBox, types.Money),
                  (widget.Text, types.Decimal),
                  (widget.SpinBox, types.Decimal)])
    def _compatible(self, one, another):
        return True

    @multimethod(widget.WidgetType, types.Type)
    def _compatible(self, one, another):
        return False
