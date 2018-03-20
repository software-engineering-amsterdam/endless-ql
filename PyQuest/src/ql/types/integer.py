from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.types.decimal import QLDecimal
from ql.ast.expressions.literals.integer_node import IntegerNode
from gui.widgets.spinbox import SpinBox


class QLInteger(QLType):
    def __init__(self, value=0):
        super(QLInteger, self).__init__()
        self.__value = int(value)

    def __repr__(self):
        return str(self.value)

    def __neg__(self):
        return QLInteger(- self.value)

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    def __lt__(self, other):
        return QLBoolean(self.value < other.value)

    def __gt__(self, other):
        return QLBoolean(self.value > other.value)

    def __le__(self, other):
        return QLBoolean(self.value <= other.value)

    def __ge__(self, other):
        return QLBoolean(self.value >= other.value)

    def __add__(self, other):
        return QLInteger(self.value + other.value)

    def __sub__(self, other):
        return QLInteger(self.value - other.value)

    def __mul__(self, other):
        return QLInteger(self.value * other.value)

    def __floordiv__(self, other):
        return QLInteger(self.value // other.value)

    def __truediv__(self, other):
        return QLDecimal(self.value / other.value)

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=0):
        return IntegerNode(None, QLInteger, QLInteger(value))

    @staticmethod
    def pyqt5_default_widget():
        widget = SpinBox()
        maximum = 2**31 - 1
        minimum = -maximum
        widget.setRange(minimum, maximum)
        return widget
