from AST.types.type import Type
from render.widgets import CheckBox


class TypeBoolean(Type):
    def __init__(self):
        super(TypeBoolean, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'boolean'

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
