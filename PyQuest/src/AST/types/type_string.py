from AST.types.type import Type
from PyQt5.QtWidgets import QLineEdit


class TypeString(Type):
    def __init__(self):
        super(TypeString, self).__init__()
        self.operations = []

    @staticmethod
    def pyqt5_default_widget():
        return QLineEdit()
