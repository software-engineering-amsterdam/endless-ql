from argparse import ArgumentParser
from sys import argv
from sys import exit

from PyQt5.QtWidgets import QApplication

from gui.windows.main import MainWindow
from ql.parser.lexer import QLLexer
from ql.parser.parser import QLParser
from ql.test.expression_evaluation.test_expression_evaluation import TestExpressionEvaluation
from ql.test.lexing.test_lexing import TestLexing
from ql.test.parsing.test_parsing import TestParsing
from ql.test.semantic_analysis.test_semantic_analysis import TestSemanticAnalysis


def main():
    parser = ArgumentParser(prog='PyQuest')
    parser.add_argument('-t', '--test', action='store_true', help="Perform tests.")
    arguments = parser.parse_args()

    if arguments.test:
        ql_lexer = QLLexer()
        ql_lexer.build()
        ql_parser = QLParser()
        ql_parser.build()

        test_lexer = TestLexing('ql/test/lexing/', ql_lexer)
        test_parser = TestParsing('ql/test/parsing/', ql_lexer, ql_parser)
        test_semantic_analyzer = TestSemanticAnalysis('ql/test/semantic_analysis/', ql_lexer, ql_parser)
        test_evaluation = TestExpressionEvaluation('ql/test/expression_evaluation/', ql_lexer, ql_parser)

        test_lexer.test()
        test_parser.test()
        test_semantic_analyzer.test()
        test_evaluation.test()
    else:
        application = QApplication(argv)
        main_window = MainWindow()
        exit(application.exec_())


if __name__ == '__main__':
    main()
