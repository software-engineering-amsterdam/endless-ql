from AST.types.type import Type
from PyQt5.QtWidgets import QCalendarWidget


class TypeDate(Type):
    def __init__(self):
        super(TypeDate, self).__init__()
        self.operations = []

    @staticmethod
    def pyqt5_default_widget():
        return QCalendarWidget()
