from ql.types.type import Type
from ql.ast.expressions.literals.integer_node import IntegerNode
from gui.model.widgets import SpinBox


class QLInteger(Type):
    def __init__(self, value):
        super(QLInteger, self).__init__()
        self.__value = value

    def __repr__(self):
        return str(self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return int(value)

    @staticmethod
    def get_literal_node(value):
        return IntegerNode(None, QLInteger, value)

    @staticmethod
    def pyqt5_default_widget():
        widget = SpinBox()
        maximum = 2 ^ 31
        minimum = -maximum
        widget.setRange(minimum, maximum)
        return widget
