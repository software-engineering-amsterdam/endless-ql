from gui.widgets.double_spinbox import DoubleSpinBox
from ql.ast.nodes.expressions.literals.decimal_literal import DecimalNode
from ql.types.boolean import QLBoolean
from ql.types.money import QLMoney
from ql.types.type import QLType


class QLDecimal(QLType):
    def __init__(self, value=0.0):
        super(QLDecimal, self).__init__()
        self.__value = float(value)

    @property
    def value(self):
        return self.__value

    def __repr__(self):
        return str(self.value)

    def __str__(self):
        return str(self.value)

    def __neg__(self):
        return QLDecimal(- self.value)

    def __eq__(self, other):
        if isinstance(other, QLDecimal):
            return QLBoolean(self.value == other.value)

        return QLBoolean(False)

    def __ne__(self, other):
        return QLBoolean(not self == other)

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
        if isinstance(other, QLMoney):
            return QLMoney(self.value * other.value, other.currency)

        return QLDecimal(self.value * other.value)

    def __truediv__(self, other):
        if isinstance(other, QLMoney):
            return QLMoney(other.value / self.value, other.currency)

        return QLDecimal(self.value / other.value)

    def get_json_value(self):
        return self.value

    @staticmethod
    def get_literal_node(value):
        return DecimalNode(None, QLDecimal, value)

    @staticmethod
    def pyqt5_default_widget():
        return DoubleSpinBox()
