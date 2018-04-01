from datetime import date

from gui.widgets.calendar import CalendarWidget
from ql.ast.nodes.expressions.literals.date_literal import DateNode
from ql.types.boolean import QLBoolean
from ql.types.type import QLType


class QLDate(QLType):
    def __init__(self, day=1, month=1, year=1):
        super(QLDate, self).__init__()
        self.__day = int(day)
        self.__month = int(month)
        self.__year = int(year)
        date(self.year, self.month, self.day)

    @property
    def day(self):
        return self.__day

    @property
    def month(self):
        return self.__month

    @property
    def year(self):
        return self.__year

    def __repr__(self):
        return '{}-{}-{}'.format(self.day, self.month, self.year)

    def __str__(self):
        return '{}-{}-{}'.format(self.day, self.month, self.year)

    def __eq__(self, other):
        if isinstance(other, QLDate):
            return QLBoolean(self.day == other.day and self.month == other.month and self.year == other.year)

        return QLBoolean(False)

    def __ne__(self, other):
        return QLBoolean(not self == other)

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

    def get_json_value(self):
        return {'day': self.day, 'month': self.month, 'year': self.year}

    @staticmethod
    def get_literal_node(value):
        return DateNode(None, QLDate, value)

    @staticmethod
    def pyqt5_default_widget():
        return CalendarWidget()
