from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.ast.expressions.literals.undefined_node import UndefinedNode
from gui.model.widgets import Label


class QLUndefined(QLType):
    def __init__(self):
        super(QLUndefined, self).__init__()
        self.__value = None

    def __repr__(self):
        return 'QLNone'

    def __eq__(self, other):
        return QLBoolean(self.value == other.value)

    def __ne__(self, other):
        return QLBoolean(self.value != other.value)

    @property
    def value(self):
        return self.__value

    @staticmethod
    def get_literal_node():
        return UndefinedNode(None, QLUndefined, None)

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
