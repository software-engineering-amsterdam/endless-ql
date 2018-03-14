from AST.types.type import Type
from AST.expressions.literals.string_node import StringNode
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
    def get_literal_node(value):
        return StringNode(None, TypeString, value)

    @staticmethod
    def pyqt5_default_widget():
        return LineEdit()
