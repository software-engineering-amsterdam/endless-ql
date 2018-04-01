from antlr4 import *
from antlr4.error.ErrorListener import ErrorListener

from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from pyql.ast.parse_tree_visitor import ParseTreeVisitor as QLParseTreeVisitor
from pyql.gui.gui import GUI
from pyql.static_analysis.collector import Collector
from pyql.static_analysis.static_checker import StaticChecker
from pyqls.antlr.QLSLexer import QLSLexer
from pyqls.antlr.QLSParser import QLSParser
from pyqls.ast.parse_tree_visitor import ParseTreeVisitor as QLSParseTreeVisitor
from pyqls.static_analysis.compatibility import CompatibilityTypesWidget
from pyqls.static_analysis.questions import CheckQuestionsInQL
from util.message import Error
from util.message_handler import MessageHandler
import tkinter as tk
from tkinter import filedialog
import os

filenames = [
    "pyql/antlr/example.ql",
    "pyql/test/samples/example.ql",
    "pyql/test/samples/form1.ql",
    "pyql/test/samples/form2.ql",
    "pyql/test/samples/form3.ql",
    "pyql/test/samples/form4.ql",
    "samples/qls/example.qls",
    "samples/qls/valid.qls",
    "samples/ql/valid.ql"
]


def parse_ql(file):
    try:
        input = FileStream(file)
        lexer = QLLexer(input)
        stream = CommonTokenStream(lexer)
        parser = QLParser(stream)
        parser._listeners = ErrorListener()
        return parser.form()
    except TypeError:

        return None


def parse_qls(file):
    try:
        input = FileStream(file)
        lexer = QLSLexer(input)
        stream = CommonTokenStream(lexer)
        parser = QLSParser(stream)
        parser._listeners = ErrorListener()
        return parser.qlsObject()
    except TypeError:
        return None


def ql(filename):
    visitor = QLParseTreeVisitor()
    parse_tree = parse_ql(filename)
    if parse_tree is None:
        MessageHandler().add(Error("Failed to parse " + filename))
        GUI(None)
        return
    ast = parse_tree.accept(visitor)
    static_checker = StaticChecker()
    static_checker.run(ast)
    GUI(ast)
    print(MessageHandler().messages)


def ql_qls(filename):
    qls_visitor = QLSParseTreeVisitor()
    qls_parse_tree = parse_qls(filename)
    if qls_parse_tree is None:
        MessageHandler().add(Error("Failed to parse " + filename))
        GUI(None)
        return

    qls_ast = qls_parse_tree.accept(qls_visitor)
    ql_visitor = QLParseTreeVisitor()
    ql_parse_tree = parse_ql(qls_ast.ql_filename)

    if ql_parse_tree is None:
        MessageHandler().add(Error("Failed to parse QL file" + qls_ast.ql_filename))
        GUI(None)
        return

    ql_ast = ql_parse_tree.accept(ql_visitor)
    static_checker = StaticChecker()
    static_checker.run(ql_ast)
    questions_table = Collector().collect_questions(ql_ast)
    CheckQuestionsInQL(questions_table).check(qls_ast)
    CompatibilityTypesWidget(questions_table).check(qls_ast)
    GUI(ql_ast)


if __name__ == '__main__':
    root = tk.Tk()
    root.withdraw()
    file_path = filedialog.askopenfilename(filetypes=[("ql(s) file", ("*.ql", "*.qls"))])
    root.destroy()

    _, file_extension = os.path.splitext(file_path)

    if file_extension == "ql":
        ql(file_path)
    else:
        ql_qls(file_path)
