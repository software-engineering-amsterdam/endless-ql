from ql.parser.lexer import QLLexer
from ql.parser.parser import QLParser
from tests.expression_evaluation.test_expression_evaluation import TestExpressionEvaluation
from tests.lexing.test_lexing import TestLexing
from tests.parsing.test_parsing import TestParsing
from tests.semantic_analysis.test_semantic_analysis import TestSemanticAnalysis


if __name__ == '__main__':
    ql_lexer = QLLexer()
    ql_lexer.build()
    ql_parser = QLParser()
    ql_parser.build()

    test_lexer = TestLexing('lexing/', ql_lexer)
    test_parser = TestParsing('parsing/', ql_lexer, ql_parser)
    test_semantic_analyzer = TestSemanticAnalysis('semantic_analysis/', ql_lexer, ql_parser)
    test_evaluation = TestExpressionEvaluation('expression_evaluation/', ql_lexer, ql_parser)

    test_lexer.test()
    test_parser.test()
    test_semantic_analyzer.test()
    test_evaluation.test()