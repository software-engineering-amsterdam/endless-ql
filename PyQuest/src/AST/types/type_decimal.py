from AST.types.type import Type
from PyQt5.QtWidgets import QLineEdit


class TypeDecimal(Type):
    def __init__(self):
        super(TypeDecimal, self).__init__()
        self.operations = []

    @staticmethod
    def pyqt5_default_widget():
        return QLineEdit()
