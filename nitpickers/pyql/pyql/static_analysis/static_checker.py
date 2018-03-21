from pyql.static_analysis.duplication import CheckDuplicatedQuestions
from pyql.static_analysis.dependency import VariableDependenciesChecker
from multimethods import multimethod
from pyql.ast.expression.expressions import *
from pyql.util.message_handler import MessageHandler
from pyql.util import message
from pyql.ast.form.form import *
from pyql.ast.form.ql_statements import *
from pyql.ast.form.block import *
from pyql.ast.form.statement import *

class StaticChecker:
    def __init__(self):
        self._messages = []

    def run(self, tree):
        print("****** Check Duplicated Questions *****")
        cdq = CheckDuplicatedQuestions()
        cdq.check(tree)

        print("****** Variable Dependencies Checker ******")
        vdc = VariableDependenciesChecker()
        vdc.check(tree)

        cdbz = CheckDivisionByZero()
        cdbz.check(tree)



    def messages(self):
        return self._messages
        # stb = SymbolTableBuilder()
        # st = stb.build_from_tree(c)
        #
        # print(st)
        #
        # vv = ASTVisitor(stb.symbol_table)
        # c.accept(vv)


class CheckDivisionByZero:

    def check(self, expression):
        expression.accept(self)

    @multimethod(Division)
    def visit(self, expression):
        expression.left.accept(self)
        expression.left.accept(self)

    @multimethod(BinaryExpression)
    def visit(self, expression):
        expression.left.accept(self)
        expression.right.accept(self)

    @multimethod(UnaryExpression)
    def visit(self, unary_expression):
        unary_expression.expression.accept(self)

    @multimethod(Expression)
    def visit(self, _):
        pass

    @multimethod(Form)
    def visit(self, form):
        form.block.accept(self)

    @multimethod(Block)
    def visit(self, block):
        questions = block.statements
        for q in questions:
            q.accept(self)

    @multimethod(ComputedQuestion)
    def visit(self, computed_question):
        computed_question.expression.accept(self)

    @multimethod(IfElse)
    def visit(self, if_else_statement):
        if_else_statement.expression.accept(self)
        if_else_statement.if_block.accept(self)
        if_else_statement.else_block.accept(self)

    @multimethod(If)
    def visit(self, if_statement):
        if_statement.expression.accept(self)
        if_statement.block.accept(self)

    @multimethod(ASTNode)
    def visit(self, node):
        pass
