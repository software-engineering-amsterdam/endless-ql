from AST.types.type import Type
from render.widgets import CalendarWidget


class TypeDate(Type):
    def __init__(self, day, month, year):
        super(TypeDate, self).__init__()
        self.__day = day
        self.__month = month
        self.__year = year

    def __repr__(self):
        return '{}-{}-{}'.format(self.__day, self.__month, self.__year)

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def cast(value):
        return

    @staticmethod
    def pyqt5_default_widget():
        return CalendarWidget()
