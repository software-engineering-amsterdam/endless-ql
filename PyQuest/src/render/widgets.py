from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QSpinBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QCalendarWidget


class Label(QLabel):
    def __init__(self, label):
        super(QLabel, self).__init__(label)

    def value(self):
        return self.text()

    def onChange(self, change_event_function):
        pass


class LineEdit(QLineEdit):
    def __init__(self):
        super(QLineEdit, self).__init__()

    def value(self):
        return self.text()

    def onChange(self, change_event_function):
        self.textChanged.connect(lambda: change_event_function(self))


class CheckBox(QCheckBox):
    def __init__(self):
        super(QCheckBox, self).__init__()

    def value(self):
        return self.isChecked()

    def onChange(self, change_event_function):
        self.stateChanged.connect(lambda: change_event_function(self))


class SpinBox(QSpinBox):
    def __init__(self):
        super(QSpinBox, self).__init__()

    def onChange(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))


class DoubleSpinBox(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()

    def onChange(self, change_event_function):
        self.valueChanged.connect(lambda: change_event_function(self))


class CalendarWidget(QCalendarWidget):
    def __init__(self):
        super(QCalendarWidget, self).__init__()

    def value(self):
        date = self.selectedDate()
        return date.day(), date.month(), date.year()

    def onChange(self, change_event_function):
        self.selectionChanged.connect(lambda: change_event_function(self))
