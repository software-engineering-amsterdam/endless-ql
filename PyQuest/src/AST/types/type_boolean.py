from AST.types.type import Type
from render.widgets import CheckBox


class TypeBoolean(Type):
    def __init__(self):
        super(TypeBoolean, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'boolean'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
