from AST.types.type import Type
from render.widgets import LineEdit


class TypeString(Type):
    def __init__(self):
        super(TypeString, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'string'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def pyqt5_default_widget():
        return LineEdit()
