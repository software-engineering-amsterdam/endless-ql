from AST.types.type import Type
from AST.expressions.literals.boolean_node import BooleanNode
from render.widgets import CheckBox


class TypeBoolean(Type):
    def __init__(self):
        super(TypeBoolean, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'boolean'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return bool(value)

    @staticmethod
    def get_literal_node(value):
        return BooleanNode(None, TypeBoolean, value)

    @staticmethod
    def pyqt5_default_widget():
        return CheckBox()
