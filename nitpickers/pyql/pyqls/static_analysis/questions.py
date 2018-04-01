from util.multimethods import multimethod
from pyqls.ast.nodes.statement import Question
from pyqls.ast.nodes.statement import QuestionStyle
from pyqls.ast.nodes.stylesheet import StyleSheet
from pyqls.ast.nodes.block import Block
from pyqls.ast.nodes.page import Page
from pyqls.ast.nodes.section import Section
from pyqls.ast.nodes.section import InlineSection
from util.ast import ASTNode
from util.message_handler import MessageHandler
from util.message import *
from functools import reduce

# checks:
#   no references to questions that are not in the QL program
#   all questions of the QL are place by QLS, and only once.


class CheckQuestionsInQL:

    def __init__(self, ql_questions_table):
        self._ql_questions_table = dict.fromkeys(ql_questions_table, False)
        self._qls_questions = {}

    # returns true if errors are found
    def check(self, tree):
        errors_found = tree.accept(self)
        for undefined_in_qls in [x for x in self._ql_questions_table if not self._ql_questions_table[x]]:
            MessageHandler().add(Error("Question {0} from QL undefined by QLS!".format(undefined_in_qls)))
            errors_found = errors_found or True
        return errors_found

    @multimethod(Question)
    def visit(self, question):
        return self._check_question(question.identifier)

    @multimethod(QuestionStyle)
    def visit(self, question_style):
        return self._check_question(question_style.identifier)

    @multimethod(InlineSection)
    def visit(self, inline_section):
        return self._check_question(inline_section.question)

    def _check_question(self, question):
        if question not in self._ql_questions_table:
            MessageHandler().add(Error("Question {0} undefined!".format(question)))
            return True
        self._ql_questions_table[question] = True
        if question in self._qls_questions:
            MessageHandler().add(Error("Question {0} defined twice!".format(question)))
            return True
        self._qls_questions[question] = True
        return False

    @multimethod(Block)
    def visit(self, block):
        return reduce((lambda x, y: x or y), [x.accept(self) for x in block.statements])

    @multimethod(StyleSheet)
    def visit(self, stylesheet):
        return stylesheet.block.accept(self)

    @multimethod(Page)
    def visit(self, page):
        return page.block.accept(self)

    @multimethod(Section)
    def visit(self, section):
        return section.block.accept(self)

    @multimethod(ASTNode)
    def visit(self, _):
        return False
