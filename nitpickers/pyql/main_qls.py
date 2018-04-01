from antlr4 import *
from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor
from pyql.static_analysis.static_checker import StaticChecker
from pyqls.ast import parse_tree_visitor
from pyqls.antlr.QLSLexer import QLSLexer
from pyqls.antlr.QLSParser import QLSParser
from pyqls.static_analysis.questions import CheckQuestionsInQL
from pyql.static_analysis.collector import Collector
from util.message_handler import MessageHandler

filenames = [
    "pyql/test/samples/example.ql",
    "pyql/test/samples/form1.ql",
    "pyql/test/samples/form2.ql",
    "pyql/test/samples/form3.ql",
    "pyql/test/samples/form4.ql",
    "pyqls/test/samples/example.qls"
]


def parse_ql(file):
    input = FileStream(file)
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    return parser.form()


def parse_qls(file):
    input = FileStream(file)
    lexer = QLSLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLSParser(stream)
    return parser.styleSheet()


def ql():
    visitor = ParseTreeVisitor()
    ast = parse_ql(filenames[1]).accept(visitor)
    static_checker = StaticChecker()
    static_checker.run(ast)

    print(MessageHandler().messages)


def ql_qls():
    ql_visitor = ParseTreeVisitor()
    ql_ast = parse_ql(filenames[1]).accept(ql_visitor)
    static_checker = StaticChecker()
    static_checker.run(ql_ast)
    questions_table = Collector().collect_questions(ql_ast)

    qls_visitor = parse_tree_visitor.ParseTreeVisitor()
    qls_ast = parse_qls(filenames[5]).accept(qls_visitor)

    CheckQuestionsInQL(questions_table).check(qls_ast)

    print(MessageHandler().messages)


if __name__ == '__main__':
    # ql()
    ql_qls()
