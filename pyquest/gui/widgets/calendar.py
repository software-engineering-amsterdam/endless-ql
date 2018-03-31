from PyQt5.QtWidgets import QCalendarWidget


class CalendarWidget(QCalendarWidget):
    def __init__(self):
        super(QCalendarWidget, self).__init__()

    def value(self):
        date = self.selectedDate()
        return date.day(), date.month(), date.year()

    def on_change(self, change_event_function):
        self.selectionChanged.connect(lambda: change_event_function(self))
