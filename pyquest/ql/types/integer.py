from gui.widgets.spinbox import SpinBox
from ql.ast.nodes.expressions.literals.integer_literal import IntegerNode
from ql.types.boolean import QLBoolean
from ql.types.decimal import QLDecimal
from ql.types.money import QLMoney
from ql.types.type import QLType


class QLInteger(QLType):
    def __init__(self, value=0):
        super(QLInteger, self).__init__()
        self.__value = int(value)

    @property
    def value(self):
        return self.__value

    def __repr__(self):
        return str(self.value)

    def __str__(self):
        return str(self.value)

    def __neg__(self):
        return QLInteger(- self.value)

    def __eq__(self, other):
        if isinstance(other, QLInteger):
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
        return type(other)(self.value + other.value)

    def __sub__(self, other):
        return type(other)(self.value - other.value)

    def __mul__(self, other):
        if isinstance(other, QLMoney):
            return QLMoney(self.value * other.value, other.currency)

        return type(other)(self.value * other.value)

    def __truediv__(self, other):
        if isinstance(other, QLMoney):
            return QLMoney(other.value / self.value, other.currency)

        return QLDecimal(self.value / other.value)

    def get_json_value(self):
        return self.value

    @staticmethod
    def get_literal_node(value):
        return IntegerNode(None, QLInteger, value)

    @staticmethod
    def pyqt5_default_widget():
        return SpinBox()
