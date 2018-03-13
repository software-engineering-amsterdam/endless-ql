from AST.types.type import Type
from AST.expressions.literals.decimal_node import DecimalNode
from render.widgets import DoubleSpinBox


class TypeDecimal(Type):
    def __init__(self):
        super(TypeDecimal, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'decimal'

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
