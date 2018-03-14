from AST.types.type import Type
from AST.expressions.literals.undefined_node import UndefinedNode
from render.widgets import Label


class TypeUndefined(Type):
    def __init__(self):
        super(TypeUndefined, self).__init__()
        self.__value = None

    def __repr__(self):
        return str(self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return

    @staticmethod
    def get_literal_node():
        return UndefinedNode(None, TypeUndefined, None)

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
