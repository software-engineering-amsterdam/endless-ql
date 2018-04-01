from ql.ast.visitors.dependency_visitor import DependencyVisitor
from ql.ast.visitors.gui_model_generator import GUIModel
from ql.ast.visitors.identifier_type_visitor import IdentifierTypeVisitor
from ql.ast.visitors.question_visitor import QuestionVisitor
from ql.ast.visitors.reference_visitor import ReferenceVisitor


def extract_identifier_dependencies(ast):
    dependency_visitor = DependencyVisitor()
    dependency_visitor.visit(ast)
    return dependency_visitor.identifier_dependencies


def extract_identifier_scopes(ast):
    reference_visitor = ReferenceVisitor()
    reference_visitor.visit(ast)
    return reference_visitor.identifier_scopes


def extract_identifier_types(ast):
    identifier_type_visitor = IdentifierTypeVisitor()
    identifier_type_visitor.visit(ast)
    return identifier_type_visitor.symbol_table


def extract_questions(ast):
    question_visitor = QuestionVisitor()
    question_visitor.visit(ast)
    return question_visitor.questions


def extract_gui_model(ast):
    gui_model_generator = GUIModel()
    gui_model_generator.visit(ast)
    return gui_model_generator.form
