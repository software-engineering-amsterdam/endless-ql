from ql.parser.lexer import QLLexer
from ql.parser.parser import QLParser

from src.test.lexing.test_lexing import TestLexing
from src.test.parsing.test_parsing import TestParsing


if __name__ == '__main__':
    ql_parser = QLParser()
    ql_lexer = QLLexer()

    test_lexer = TestLexing('lexing/', ql_lexer)
    test_parser = TestParsing('parsing/', ql_lexer, ql_parser)

    test_lexer.test()
    test_parser.test()
