from gui.widgets.checkbox import CheckBox
from ql.ast.nodes.expressions.literals.boolean_literal import BooleanNode
from ql.types.type import QLType


class QLBoolean(QLType):
    def __init__(self, value=False):
        super(QLBoolean, self).__init__()
        self.__value = bool(value)

    @property
    def value(self):
        return self.__value

    def __bool__(self):
        return bool(self.value)

    def __str__(self):
        return str(self.value)

    def __repr__(self):
        return str(self.value)

    def __eq__(self, other):
        if isinstance(other, QLBoolean):
            return QLBoolean(self.value == other.value)

        return QLBoolean(False)

    def __ne__(self, other):
        return QLBoolean(not self == other)

    def get_json_value(self):
        return self.value

    @staticmethod
    def get_literal_node(value):
        return BooleanNode(None, QLBoolean, value)

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
