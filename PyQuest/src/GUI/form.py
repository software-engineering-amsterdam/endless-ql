from PyQt5.QtWidgets import QApplication, QDialog
from src.visitors.render import Render
from src.scanparse.qllex import LexTokenizer
from src.scanparse.qlyacc import QLParser

import sys


class Form(QDialog):
    def __init__(self, ast):
        super(QDialog, self).__init__()

        self.dialog = QDialog()
        self.ast = ast
        self.build()

        sys.exit(self.dialog.exec_())

    def build(self):
        visitor = Render(self.dialog)
        visitor.visit(self.ast)


if __name__ == '__main__':
    with open('../tests/test3.ql') as f:
        data = f.read()

    parser = QLParser()
    lexer = LexTokenizer()
    ast = parser.parser.parse(data, lexer.lexer)

    app = QApplication(sys.argv)
    Form(ast)
