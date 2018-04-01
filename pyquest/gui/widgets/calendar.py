from PyQt5.QtWidgets import QCalendarWidget


class CalendarWidget(QCalendarWidget):
    def __init__(self):
        super(QCalendarWidget, self).__init__()

    def get_value(self, ql_type):
        date = self.selectedDate()
        return ql_type(date.day(), date.month(), date.year())

    def on_change(self, change_event_function):
        self.selectionChanged.connect(lambda: change_event_function(self))
