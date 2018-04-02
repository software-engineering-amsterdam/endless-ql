from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QTextEdit

from gui.helper import append_file_extension
from gui.helper import check_errors
from gui.helper import warning
from gui.windows.form import FormWindow
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
        ql_lexer.build()
        ql_parser = QLParser()
        ql_parser.build()
        empty_form = []

        if not textbox_value:
            empty_form.append('Empty Form')

        try:
            check_errors(empty_form)
            ast = ql_parser.parse(textbox_value, ql_lexer.lexer)
            parse_errors = ql_parser.errors

            check_errors(parse_errors)
            reference_errors = ReferenceChecker(extract_identifier_scopes(ast)).errors

            check_errors(reference_errors)
            dependency_errors = DependencyChecker(extract_identifier_dependencies(ast)).errors

            check_errors(dependency_errors)
            question_errors = QuestionChecker(extract_questions(ast)).errors
            question_warnings = QuestionChecker(extract_questions(ast)).warnings

            if question_warnings:
                warning(question_warnings)

            check_errors(question_errors)
            type_visitor = TypeVisitor(extract_identifier_types(ast))
            type_visitor.visit(ast)
            type_errors = type_visitor.errors

            check_errors(type_errors),
            dialog = FormWindow(extract_gui_model(ast))
            dialog.exec_()
        except SyntaxError:
            print('Consult application popup error window for information on what went wrong.')
