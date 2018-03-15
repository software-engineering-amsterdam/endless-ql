from ql.types.type import Type
from ql.ast.expressions.literals.decimal_node import DecimalNode
from gui.model.widgets import DoubleSpinBox


class TypeDecimal(Type):
    def __init__(self, value):
        super(TypeDecimal, self).__init__()
        self.__value = value

    def __repr__(self):
        return str(self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return float(value)

    @staticmethod
    def get_literal_node(value):
        return DecimalNode(None, TypeDecimal, value)

    @staticmethod
    def pyqt5_default_widget():
        number_of_decimals = 16
        widget = DoubleSpinBox()
        widget.setDecimals(number_of_decimals)
        return widget
