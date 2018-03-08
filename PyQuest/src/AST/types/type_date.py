from AST.types.type import Type
from render.widgets import CalendarWidget


class TypeDate(Type):
    def __init__(self):
        super(TypeDate, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'date'

    @staticmethod
    def pyqt5_default_widget():
        return CalendarWidget()
