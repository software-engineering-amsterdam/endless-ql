from AST.types.type import Type
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
    def pyqt5_default_widget():
        number_of_decimals = 16
        widget = DoubleSpinBox()
        widget.setDecimals(number_of_decimals)
        return widget
