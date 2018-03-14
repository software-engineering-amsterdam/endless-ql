from AST.types.type import Type
from AST.expressions.literals.money_node import MoneyNode
from render.widgets import DoubleSpinBox


class TypeMoney(Type):
    def __init__(self, value, currency=''):
        super(TypeMoney, self).__init__()
        self.__value = value
        self.__currency = currency

    def __repr__(self):
        return '{}{:.2f}'.format(self.__currency, self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return round(float(value), 2)

    @staticmethod
    def get_literal_node(value):
        return MoneyNode(None, TypeMoney, value)

    @staticmethod
    def pyqt5_default_widget():
        return DoubleSpinBox()
