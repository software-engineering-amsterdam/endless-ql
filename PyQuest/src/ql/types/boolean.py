from ql.types.type import QLType
from ql.ast.expressions.literals.boolean_node import BooleanNode
from gui.model.widgets import CheckBox


class QLBoolean(QLType):
    def __init__(self, value=False):
        super(QLBoolean, self).__init__()
        self.__value = bool(value)

    def __bool__(self):
        return self.value

    def __repr__(self):
        return str(self.value)

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=False):
        return BooleanNode(None, QLBoolean, QLBoolean(value))

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
