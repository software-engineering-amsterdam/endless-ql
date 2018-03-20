from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.ast.expressions.literals.undefined_node import UndefinedNode
from gui.widgets.label import Label


class QLUndefined(QLType):
    def __init__(self, value=None):
        super(QLUndefined, self).__init__()
        self.__value = None

    def __bool__(self):
        return False

    def __repr__(self):
        return 'Undefined'

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node(value=None):
        return UndefinedNode(None, QLUndefined, QLUndefined(value))

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
