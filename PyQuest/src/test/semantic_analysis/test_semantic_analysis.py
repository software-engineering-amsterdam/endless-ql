from src.test.test import Test
from ql.ast.checkers.dependency_checker import DependencyChecker
from ql.ast.checkers.question_checker import QuestionChecker
from ql.ast.checkers.reference_checker import ReferenceChecker
from ql.ast.visitors.type_visitor import TypeVisitor
from ql.ast.extractors.extractor import extract_identifier_dependencies
from ql.ast.extractors.extractor import extract_identifier_scopes
from ql.ast.extractors.extractor import extract_identifier_types
from ql.ast.extractors.extractor import extract_questions


class TestSemanticAnalysis(Test):
    def __init__(self, directory, lexer, parser):
        super(TestSemanticAnalysis, self).__init__('semantic analysis', directory)
        self.lexer = lexer
        self.parser = parser

    def test_file(self, file):
        ast = self.parser.parse(file, self.lexer.lexer)

        reference_errors = ReferenceChecker(extract_identifier_scopes(ast)).errors
        dependency_errors = DependencyChecker(extract_identifier_dependencies(ast)).errors
        question_errors = QuestionChecker(extract_questions(ast)).errors

        type_visitor = TypeVisitor(extract_identifier_types(ast))
        type_visitor.visit(ast)
        type_errors = type_visitor.errors

        errors = reference_errors + dependency_errors + question_errors + type_errors
        if errors:
            return False
        return True
