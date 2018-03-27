from gui.widgets.double_spinbox import DoubleSpinBox
from ql.ast.nodes.expressions.literals.decimal_node import DecimalNode
from ql.types.boolean import QLBoolean
from ql.types.type import QLType


class QLDecimal(QLType):
    def __init__(self, value=0.0):
        super(QLDecimal, self).__init__()
        self.__value = float(value)

    def __repr__(self):
        return str(self.value)

    def __bool__(self):
        return bool(self.value)

    def __float__(self):
        return float(self.value)

    def __int__(self):
        return int(self.value)

    def __str__(self):
        return str(self.value)

    def __neg__(self):
        return QLDecimal(- self.value)

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
        return QLDecimal(self.value + other.value)

    def __sub__(self, other):
        return QLDecimal(self.value - other.value)

    def __mul__(self, other):
        return QLDecimal(self.value * other.value)

    def __floordiv__(self, other):
        return QLDecimal(self.value // other.value)

    def __truediv__(self, other):
        return QLDecimal(self.value / other.value)

    def get_json_value(self):
        return self.value

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=0.0):
        return DecimalNode(None, QLDecimal, QLDecimal(value))

    @staticmethod
    def pyqt5_default_widget():
        number_of_decimals = 16
        widget = DoubleSpinBox()
        widget.setDecimals(number_of_decimals)
        return widget
