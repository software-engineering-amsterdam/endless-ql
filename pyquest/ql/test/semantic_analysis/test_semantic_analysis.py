from ql.ast.checkers.dependency_checker import DependencyChecker
from ql.ast.checkers.question_checker import QuestionChecker
from ql.ast.checkers.reference_checker import ReferenceChecker
from ql.ast.extractors.extractor import extract_identifier_dependencies
from ql.ast.extractors.extractor import extract_identifier_scopes
from ql.ast.extractors.extractor import extract_identifier_types
from ql.ast.extractors.extractor import extract_questions
from ql.ast.visitors.type_visitor import TypeVisitor
from ql.test.test import Test


class TestSemanticAnalysis(Test):
    def __init__(self, directory, lexer, parser):
        super(TestSemanticAnalysis, self).__init__('semantic analysis', directory)
        self.__lexer = lexer
        self.__parser = parser

    def test_file(self, file):
        ast = self.__parser.parse(file, self.__lexer.lexer)

        reference_errors = ReferenceChecker(extract_identifier_scopes(ast)).errors
        if reference_errors:
            return False

        dependency_errors = DependencyChecker(extract_identifier_dependencies(ast)).errors
        if dependency_errors:
            return False

        question_errors = QuestionChecker(extract_questions(ast)).errors
        if question_errors:
            return False

        type_visitor = TypeVisitor(extract_identifier_types(ast))
        type_visitor.visit(ast)
        type_errors = type_visitor.errors
        if type_errors:
            return False

        return True
