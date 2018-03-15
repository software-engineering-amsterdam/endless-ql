from ql.types.type import Type
from ql.ast.expressions.literals.boolean_node import BooleanNode
from gui.model.widgets import CheckBox


class QLBoolean(Type):
    def __init__(self, value):
        super(QLBoolean, self).__init__()
        self.__value = value

    def __repr__(self):
        return str(self.__value)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return bool(value)

    @staticmethod
    def get_literal_node(value):
        return BooleanNode(None, QLBoolean, value)

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
