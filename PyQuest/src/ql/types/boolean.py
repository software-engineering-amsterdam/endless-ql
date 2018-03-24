from gui.widgets.checkbox import CheckBox
from ql.ast.expressions.literals.boolean_node import BooleanNode
from ql.types.type import QLType


class QLBoolean(QLType):
    def __init__(self, value=False):
        super(QLBoolean, self).__init__()
        self.__value = bool(value)

    def __bool__(self):
        return bool(self.value)

    def __float__(self):
        return float(self.value)

    def __int__(self):
        return int(self.value)

    def __str__(self):
        return str(self.value)

    def __repr__(self):
        return str(self.value)

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    def get_json_value(self):
        return self.value

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=False):
        return BooleanNode(None, QLBoolean, QLBoolean(value))

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
