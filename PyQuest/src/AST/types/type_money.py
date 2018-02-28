from AST.types.type import Type
from PyQt5.QtWidgets import QLineEdit


class TypeMoney(Type):
    def __init__(self):
        super(TypeMoney, self).__init__()
        self.operations = []

    @staticmethod
    def pyqt5_default_widget():
        return QLineEdit()
