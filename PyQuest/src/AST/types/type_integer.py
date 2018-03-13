from AST.types.type import Type
from AST.expressions.literals.integer_node import IntegerNode
from render.widgets import SpinBox


class TypeInteger(Type):
    def __init__(self):
        super(TypeInteger, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'integer'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return int(value)

    @staticmethod
    def get_literal_node(value):
        return IntegerNode(None, TypeInteger, value)

    @staticmethod
    def pyqt5_default_widget():
        widget = SpinBox()
        maximum = 2 ^ 31
        minimum = -maximum
        widget.setRange(minimum, maximum)
        return widget
