from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.ast.expressions.literals.money_node import MoneyNode
from gui.model.widgets import DoubleSpinBox


class QLMoney(QLType):
    def __init__(self, value=0.0, currency=''):
        super(QLMoney, self).__init__()
        self.__value = float(value)
        self.__currency = currency

    def __repr__(self):
        return '{}{:.2f}'.format(self.currency, self.value)

    def __eq__(self, other):
        return QLBoolean(self.value == other.value and self.currency == other.currency)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value or self.currency != other.currency)

    def __lt__(self, other):
        return QLBoolean(self.value < other.value and self.currency == other.currency)

    def __gt__(self, other):
        return QLBoolean(self.value > other.value and self.currency == other.currency)

    def __le__(self, other):
        return QLBoolean(self.value <= other.value and self.currency == other.currency)

    def __ge__(self, other):
        return QLBoolean(self.value >= other.value and self.currency == other.currency)

    def __add__(self, other):
        return QLMoney(self.value + other.value)

    def __sub__(self, other):
        return QLMoney(self.value - other.value)

    @property
    def value(self):
        return self.__value

    @property
    def currency(self):
        return self.__currency

    @staticmethod
    def get_literal_node(value):
        return MoneyNode(None, QLMoney, value)

    @staticmethod
    def pyqt5_default_widget():
        return DoubleSpinBox()
