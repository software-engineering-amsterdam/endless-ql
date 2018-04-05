import os

from antlr4 import *

from pyql.antlr.QLLexer import QLLexer
from pyql.antlr.QLParser import QLParser
from antlr4.error.ErrorListener import ErrorListener
from pyql.ast.parse_tree_visitor import ParseTreeVisitor
from pyql.static_analysis.static_checker import StaticChecker


def run_ql_file(file):
    input = FileStream(file)
    lexer = QLLexer(input)
    stream = CommonTokenStream(lexer)
    parser = QLParser(stream)
    parser._listeners = ErrorListener()
    parse_tree = parser.form()

    visitor = ParseTreeVisitor()
    ast = parse_tree.accept(visitor)

    static_checker = StaticChecker()
    static_checker.run(ast)


def is_ql_program_valid(file):
    try:
        run_ql_file(file)
    except:
        return False
    return True


def get_ql_files_from_directory(directory):
    test_global_directory = "samples"
    test_directory = os.path.join(test_global_directory, directory)

    files = []
    for file in os.listdir(test_directory):
        if file.endswith(".ql"):
            file_path = os.path.join(test_directory, file)
            files.append(file_path)
    return files


def test_valid_ql():
    for file in get_ql_files_from_directory("valid"):
        if not is_ql_program_valid(file):
            print(SyntaxError("Syntax error in: " + file))


def test_invalid_ql():
    for file in get_ql_files_from_directory("invalid"):
        if is_ql_program_valid(file):
            print(SyntaxError("Syntax error in: " + file))


if __name__ == '__main__':
    test_valid_ql()
    test_invalid_ql()
