from AST.types.type import Type
from render.widgets import LineEdit


class TypeString(Type):
    def __init__(self, value):
        super(TypeString, self).__init__()
        self.__value = value

    def __repr__(self):
        return self.__value

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return str(value)

    @staticmethod
    def pyqt5_default_widget():
        return LineEdit()
