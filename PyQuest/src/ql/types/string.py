from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.ast.expressions.literals.string_node import StringNode
from gui.widgets.line_edit import LineEdit


class QLString(QLType):
    def __init__(self, value=''):
        super(QLString, self).__init__()
        self.__value = str(value)

    def __repr__(self):
        return str(self.value)

    def __bool__(self):
        return bool(self.value)

    def __str__(self):
        return str(self.value)

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    def __lt__(self, other):
        return QLBoolean(len(self.value) < len(other.value))

    def __gt__(self, other):
        return QLBoolean(len(self.value) > len(other.value))

    def __le__(self, other):
        return QLBoolean(len(self.value) <= len(other.value))

    def __ge__(self, other):
        return QLBoolean(len(self.value) >= len(other.value))

    def __add__(self, other):
        return QLString(self.value + other.value)

    def get_json_value(self):
        return self.value

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=''):
        return StringNode(None, QLString, QLString(value))

    @staticmethod
    def pyqt5_default_widget():
        return LineEdit()
