from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QTextEdit

from debug.debug import Debug
from gui.form_window import FormWindow
from gui.helper import append_file_extension
from ql.ast.checkers.dependency_checker import DependencyChecker
from ql.ast.checkers.question_checker import QuestionChecker
from ql.ast.checkers.reference_checker import ReferenceChecker
from ql.ast.extractors.extractor import extract_gui_model
from ql.ast.extractors.extractor import extract_identifier_dependencies
from ql.ast.extractors.extractor import extract_identifier_scopes
from ql.ast.extractors.extractor import extract_identifier_types
from ql.ast.extractors.extractor import extract_questions
from ql.ast.visitors.type_visitor import TypeVisitor
from ql.parser.lexer import QLLexer
from ql.parser.parser import QLParser


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.debug = Debug(terminal=False)
        self.setWindowTitle('PyQuest')
        self.current_file = None
        self.create_menu_bar()
        self.text_edit = QTextEdit()
        self.setCentralWidget(self.text_edit)

        self.show()

    def create_menu_bar(self):
        menu_bar = self.menuBar()

        new_file = QAction(QIcon(), 'New', self)
        new_file.setShortcut('Ctrl+N')
        new_file.setStatusTip('New file')
        new_file.triggered.connect(self.new_file)

        open_file = QAction(QIcon(), 'Open', self)
        open_file.setShortcut('Ctrl+O')
        open_file.setStatusTip('Open file')
        open_file.triggered.connect(self.open_file)

        save = QAction(QIcon(), 'Save', self)
        save.setShortcut('Ctrl+S')
        save.setStatusTip('Save file')
        save.triggered.connect(self.save)

        save_file = QAction(QIcon(), 'Save as', self)
        save_file.setShortcut('Ctrl+Shift+S')
        save_file.setStatusTip('Save file as')
        save_file.triggered.connect(self.save_file)

        create_form = QAction(QIcon(), 'Create form', self)
        create_form.setShortcut('Ctrl+R')
        create_form.setStatusTip('Create form')
        create_form.triggered.connect(self.create_form)

        file_menu = menu_bar.addMenu('&File')
        file_menu.addAction(new_file)
        file_menu.addAction(open_file)
        file_menu.addAction(save)
        file_menu.addAction(save_file)

        create_menu = menu_bar.addMenu('&Create')
        create_menu.addAction(create_form)

    def new_file(self):
        self.current_file = None
        self.text_edit.setText('')

    def open_file(self):
        self.current_file, _ = QFileDialog.getOpenFileName(caption='Open file', directory='/home',
                                                           filter="Questionnaire language files (*.ql)")

        if self.current_file:
            with open(self.current_file, 'r') as file:
                self.text_edit.setText(file.read())

    def save(self):
        if self.current_file:
            with open(self.current_file, 'w') as file:
                file.write(self.text_edit.toPlainText())
        else:
            self.save_file()

    def save_file(self):
        file_name, _ = QFileDialog.getSaveFileName(caption='Save file as', directory='/home',
                                                   filter='Questionnaire language files (*.ql)')

        if file_name:
            self.current_file = append_file_extension(file_name, 'ql')
            with open(self.current_file, 'w') as file:
                file.write(self.text_edit.toPlainText())

    def create_form(self):
        textbox_value = self.text_edit.toPlainText()

        ql_lexer = QLLexer()
        ql_parser = QLParser()

        # Nested checkers versus multiple (potentially irrelevant) dialog windows

        if textbox_value:
            ast = ql_parser.parse(textbox_value, ql_lexer.lexer)
            invalid_references = ReferenceChecker(extract_identifier_scopes(ast), self.debug).has_errors
            invalid_dependencies = DependencyChecker(extract_identifier_dependencies(ast), self.debug).has_errors
            invalid_questions = QuestionChecker(extract_questions(ast), self.debug).has_errors
            # invalid_types = TypeVisitor(extract_identifier_types(ast), self.debug).visit(ast)

            type_visitor = TypeVisitor(extract_identifier_types(ast))
            type_visitor.visit(ast)
            print(type_visitor.errors)

            if not any([invalid_references, invalid_dependencies, invalid_questions]):
                dialog = FormWindow(extract_gui_model(ast))
                dialog.exec_()

        else:
            self.debug.error([0], 'Empty Form')