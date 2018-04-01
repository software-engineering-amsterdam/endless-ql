from gui.widgets.label import Label
from ql.ast.nodes.expressions.literals.undefined_literal import UndefinedNode
from ql.types.boolean import QLBoolean
from ql.types.type import QLType


class QLUndefined(QLType):
    def __init__(self, *unused):
        super(QLUndefined, self).__init__()
        self.__value = None

    @property
    def value(self):
        return self.__value

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
        return self.value

    @staticmethod
    def get_literal_node(unused):
        return UndefinedNode(None, QLUndefined, QLUndefined(None))

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
