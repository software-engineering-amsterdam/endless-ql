from gui.widgets.money_spinbox import MoneySpinbox
from ql.ast.nodes.expressions.literals.money_literal import MoneyNode
from ql.types.boolean import QLBoolean
from ql.types.type import QLType


class QLMoney(QLType):
    def __init__(self, value=0.0, currency='$'):
        super(QLMoney, self).__init__()
        self.__value = float(value)
        self.__currency = currency

    @property
    def value(self):
        return self.__value

    @property
    def currency(self):
        return self.__currency

    def __repr__(self):
        return '{}{:.2f}'.format(self.currency, self.value)

    def __str__(self):
        return '{}{:.2f}'.format(self.currency, self.value)

    def __neg__(self):
        return QLMoney(- self.value, self.currency)

    def __eq__(self, other):
        if isinstance(other, QLMoney):
            return QLBoolean(self.value == other.value and self.currency == other.currency)

        return QLBoolean(False)

    def __ne__(self, other):
        return QLBoolean(not self == other)

    def __lt__(self, other):
        return QLBoolean(self.value < other.value and self.currency == other.currency)

    def __gt__(self, other):
        return QLBoolean(self.value > other.value and self.currency == other.currency)

    def __le__(self, other):
        return QLBoolean(self.value <= other.value and self.currency == other.currency)

    def __ge__(self, other):
        return QLBoolean(self.value >= other.value and self.currency == other.currency)

    def __add__(self, other):
        if self.currency == other.currency:
            return QLMoney(self.value + other.value, self.currency)
        return NotImplemented

    def __sub__(self, other):
        if self.currency == other.currency:
            return QLMoney(self.value - other.value, self.currency)
        return NotImplemented

    def __mul__(self, other):
        return other * self

    def __truediv__(self, other):
        return other / self

    def get_json_value(self):
        return {'value': round(self.value, 2), 'currency': self.currency}

    @staticmethod
    def get_literal_node(value):
        return MoneyNode(None, QLMoney, value)

    @staticmethod
    def pyqt5_default_widget():
        return MoneySpinbox()
