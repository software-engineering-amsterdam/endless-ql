from AST.types.type import Type
from render.widgets import Label


class TypeUndefined(Type):
    def __init__(self):
        super(TypeUndefined, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'undefined'

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
