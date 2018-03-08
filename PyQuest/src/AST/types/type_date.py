from AST.types.type import Type
from render.widgets import CalendarWidget


class TypeDate(Type):
    def __init__(self):
        super(TypeDate, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'date'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def pyqt5_default_widget():
        return CalendarWidget()
