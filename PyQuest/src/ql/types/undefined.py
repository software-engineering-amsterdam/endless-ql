from ql.types.type import Type
from ql.ast.expressions.literals.undefined_node import UndefinedNode
from gui.model.widgets import Label


class QLUndefined(Type):
    def __init__(self):
        super(QLUndefined, self).__init__()
        self.__value = None

    def __repr__(self):
        return str(self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return

    @staticmethod
    def get_literal_node():
        return UndefinedNode(None, QLUndefined, None)

    @staticmethod
    def pyqt5_default_widget():
        return Label('Undefined')
