from gui.widgets.label import Label
from ql.ast.nodes.expressions.literals.undefined_node import UndefinedNode
from ql.types.boolean import QLBoolean
from ql.types.type import QLType


class QLUndefined(QLType):
    def __init__(self, value=None):
        super(QLUndefined, self).__init__()
        self.__value = None

    def __bool__(self):
        return False

    def __repr__(self):
        return 'Undefined'

    def __eq__(self, other):
        if isinstance(other, QLUndefined):
            return QLBoolean(self.value == other.value)

        return QLBoolean(False)

    def __ne__(self, other):
        return QLBoolean(not self == other)

    def get_json_value(self):
        return None

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=None):
        return UndefinedNode(None, QLUndefined, QLUndefined(value))

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
