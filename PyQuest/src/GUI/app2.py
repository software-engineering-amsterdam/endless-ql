from PyQt5.QtWidgets import (QApplication, QComboBox, QDialog,
                             QDialogButtonBox, QFormLayout, QGridLayout, QGroupBox, QHBoxLayout,
                             QLabel, QLineEdit, QMenu, QMenuBar, QPushButton, QSpinBox, QTextEdit,
                             QVBoxLayout)
from src.visitors.render import Render

import scanparse.qlyacc as qly
import scanparse.qllex as qll
import sys


class Dialog(QDialog):
    def __init__(self):
        super(Dialog, self).__init__()

    def create_footer(self):
        buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)
        buttonBox.accepted.connect(self.accept)
        buttonBox.rejected.connect(self.reject)

        mainLayout = QVBoxLayout()
        mainLayout.addWidget(self.formGroupBox)
        mainLayout.addWidget(buttonBox)
        self.setLayout(mainLayout)

        self.setWindowTitle("Form Layout")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    dialog = Dialog()

    data = """form taxOfficeExample {
      "Did you sell a house in 2010?"
        hasSoldHouse: boolean
      "Did you buy a house in 2010?"
        hasBoughtHouse: boolean
      "Did you enter a loan?"
        hasMaintLoan: boolean

      if (hasSoldHouse) {
        "What was the selling price?"
          sellingPrice: money
        "Private debts for the sold house:"
          privateDebt: money
        "Value residue:"
          valueResidue: money =
            (11 - 10)
      }
      "Did you enter a loan?"
        hasMaintLoan2: boolean
    }"""

    p = qly.QLParser()

    l = qll.LexTokenizer()
    r = p.parser.parse(data, l.lexer)

    a = Render(dialog)
    a.visit(r)

    sys.exit(dialog.exec_())