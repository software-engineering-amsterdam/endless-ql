from AST.types.type import Type
from render.widgets import SpinBox


class TypeInteger(Type):
    def __init__(self):
        super(TypeInteger, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'integer'

    @staticmethod
    def pyqt5_default_widget():
        return SpinBox()
