from scanparse.qllex import LexTokenizer
from scanparse.qlyacc import QLParser
from visitors.render import Render
from GUI.form import Dialog
from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QMessageBox
from sys import exit
from sys import argv


class MainApp(QMainWindow):
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
        self.current_file, _ = QFileDialog.getOpenFileName(self, 'Open file', '/home', "Questionnaire language files (*.ql)")

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
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file_name, _ = QFileDialog.getSaveFileName(self, 'Save file as', '/home',
                                                   'Questionnaire language files (*.ql)', options=options)

        if file_name:
            self.current_file = file_name
            with open(self.current_file, 'w') as file:
                file.write(self.text_edit.toPlainText())

    def create_form(self):
        textbox_value = self.text_edit.toPlainText()
        parser = QLParser()
        lexer = LexTokenizer()

        try:
            ast = parser.parser.parse(textbox_value, lexer.lexer)

            # TODO type check

            visitor = Render()
            visitor.visit(ast)

            dialog = Dialog(visitor.form)
            dialog.exec_()
        except:
            QMessageBox.warning(self, 'Warning', 'Unable to create form.', QMessageBox.Ok, QMessageBox.Ok)


if __name__ == '__main__':
    app = QApplication(argv)
    ex = MainApp()
    exit(app.exec_())