from sys import exit, argv
from scanparse.qllex import LexTokenizer
from scanparse.qlyacc import QLParser
from visitors.render import Render
from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import (QApplication, QDialog,
                             QDialogButtonBox, QFormLayout, QGroupBox,
                             QVBoxLayout)


class Dialog(QDialog):
    def __init__(self, form):
        super(Dialog, self).__init__()
        self.formGroupBox = QGroupBox(form.identifier)
        self.create_form(form)

        buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)
        buttonBox.accepted.connect(self.accept)
        buttonBox.rejected.connect(self.reject)

        mainLayout = QVBoxLayout()
        mainLayout.addWidget(self.formGroupBox)
        mainLayout.addWidget(buttonBox)
        self.setLayout(mainLayout)

        self.setWindowTitle("Form")

    def create_form(self, form):
        layout = QFormLayout()

        # TODO evaluate and check show field of question
        for question in form.block:
            question.pyqt5_render(layout)

        self.formGroupBox.setLayout(layout)

    # TODO output json
    # @pyqtSlot()
    # def accept(self):
    #     pass

    # @pyqtSlot()
    # def reject(self):
    #     exit()


if __name__ == '__main__':
    with open('../tests/test3.ql') as f:
        data = f.read()

    parser = QLParser()
    lexer = LexTokenizer()
    ast = parser.parser.parse(data, lexer.lexer)

    visitor = Render()
    visitor.visit(ast)

    app = QApplication(argv)
    dialog = Dialog(visitor.form)
    exit(dialog.exec_())
