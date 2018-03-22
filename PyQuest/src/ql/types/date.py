from ql.types.type import QLType
from ql.types.boolean import QLBoolean
from ql.ast.expressions.literals.date_node import DateNode
from gui.widgets.calendar import CalendarWidget


class QLDate(QLType):
    def __init__(self, date=(1, 1, 2018)):
        super(QLDate, self).__init__()
        date = tuple(map(int, date))
        self.__day, self.__month, self.__year = date

    def __repr__(self):
        return '{}-{}-{}'.format(self.day, self.month, self.year)

    def __str__(self):
        return '{}-{}-{}'.format(self.day, self.month, self.year)

    def __eq__(self, other):
        return QLBoolean(self.day == other.day and self.month == other.month and self.year == other.year)

    def __ne__(self, other):
        return QLBoolean(self.day != other.day or self.month != other.month or self.year != other.year)

    def __lt__(self, other):
        return QLBoolean(self.year < other.year or (self.month < other.month and self.year == other.year) or
                         (self.day < other.day and self.month == other.month and self.year == other.year))

    def __gt__(self, other):
        return QLBoolean(self.year > other.year or (self.month > other.month and self.year == other.year) or
                         (self.day > other.day and self.month == other.month and self.year == other.year))

    def __le__(self, other):
        return QLBoolean(self < other or self == other)

    def __ge__(self, other):
        return QLBoolean(self > other or self == other)

    @property
    def day(self):
        return self.__day

    @property
    def month(self):
        return self.__month

    @property
    def year(self):
        return self.__year

    @staticmethod
    def get_literal_node(date=(1, 1, 2018)):
        return DateNode(None, QLDate, QLDate(date))

    @staticmethod
    def pyqt5_default_widget():
        return CalendarWidget()
