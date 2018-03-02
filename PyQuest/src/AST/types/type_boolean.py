from AST.types.type import Type
from PyQt5.QtWidgets import QCheckBox


class TypeBoolean(Type):
    def __init__(self):
        super(TypeBoolean, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'boolean'

    @staticmethod
    def pyqt5_default_widget():
        return QCheckBox()
